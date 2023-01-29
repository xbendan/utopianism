package io.myosotisdev.utopianism.modules.clan

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.minestom.Identifiable
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.Nameable
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.model.Economy
import io.myosotisdev.utopianism.modules.player.clan
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import java.util.*
import java.util.stream.Collectors

class Clan : Identifiable,
             Nameable,
             IJsonData
{
    override var name: String = ""
    override val uid: UUID
    var displayTextures: UUID? = null
    var motd: MutableList<String> = ArrayList()
    var clanMaster: ClanMember
    val members: HashMap<UUID, ClanMember> = HashMap()
    val wars: ClanWars = ClanWars()
    var tier: ClanTier = ClanTier.BRONZE
    val creationTime: Long

    var joinPolicy: ClanJoinPolicy = ClanJoinPolicy.FREE
    var playPolicy: ClanPlayPolicy = ClanPlayPolicy.FREE

    val applies: ArrayList<UUID> = ArrayList()
    val invitedMembers: ArrayList<UUID> = ArrayList()
    val allies: ArrayList<UUID> = ArrayList()
    val pendingAlly: ArrayList<UUID> = ArrayList()

    val bank: Economy = Economy()
    var friendlyFire: Boolean = false
    val clanTechs: EnumMap<ClanTech, Int> = EnumMap(ClanTech::class.java)

    var monthlyActivity: Int = 0;
    var techPoints: Int = 0
    val maxMembers: Int
        get() = 10 + (clanTechs[ClanTech.MAX_MEMBERS]!! * 2)

    constructor(uid: UUID, name: String, player: Player)
    {
        this.uid = uid
        this.name = name
        this.members.put(player.uuid, ClanMember(this, player.uuid, ClanClass.PRESIDENT).also { clanMaster = it })
        this.tier = ClanTier.BRONZE
        this.creationTime = System.currentTimeMillis()

        ClanTech.values().forEach { clanTechs[it] = 0 }

        Server.clans().clanMembers[player.uuid] = this
    }

    constructor(uid: UUID, jsonObject: JsonObject)
    {
        this.uid = uid

        val jsonManifest = jsonObject["manifest"].asJsonArray
        val jsonPlayers = jsonObject["players"].asJsonArray
        val jRegions = jsonObject["regions"].asJsonArray
        val jTechs = jsonObject["techs"].asJsonObject

        jsonPlayers.forEach { element ->
            val member = ClanMember(this, element.asJsonObject)
            members[member.playerUuid] = member
        }

        this.name = jsonObject["name"].asString
        this.motd = MutableList(jsonManifest.size()) { index -> jsonManifest.get(index).asString }
        this.creationTime = jsonObject["creationTime"].asLong
        this.clanMaster = members[UUID.fromString(jsonObject["leader"].asString)]!!
        this.tier = ClanTier.values()[jsonObject["tier"].asInt]
        this.joinPolicy = ClanJoinPolicy.valueOf(jsonObject["joinPolicy"].asString.uppercase())
        this.playPolicy = ClanPlayPolicy.valueOf(jsonObject["playPolicy"].asString.uppercase())
        this.bank.money = jsonObject["bank"].asDouble

        jRegions.forEach { element ->

        }
    }

    fun join(playerUuid: UUID)
    {
        if (Server.clans()
                        .findByPlayer(playerUuid) != null) return

        Minestom.getPlayer(playerUuid)
                ?.sendMessage("&a公会 &6&l>> &a你加入了 &e$name &a公会")
        broadcast("&a公会 &6&l>> &f玩家 &e${Server.getCustomName(playerUuid)} &f加入了公会")

        addPlayer(playerUuid)
    }

    fun kick(operator: Player, playerUuid: UUID)
    {
        val clanMember = members[playerUuid]
        if (Objects.isNull(clanMember))
        {
            return
        }
        clanMember!!.player?.sendMessage("&a公会 &6&l>> &c你被公会 &f${name} &c踢出")
        broadcast("&a公会 &6&l>> &f玩家 &e${Server.getCustomName(playerUuid)} &f被 &e${operator.customName?.examinableName() ?: operator.name} 踢出公会")

        removePlayer(playerUuid)
    }

    fun addPlayer(playerUuid: UUID)
    {
        this.members.put(playerUuid, ClanMember(this, playerUuid))
        Server.clans().clanMembers[playerUuid] = this
    }

    fun removePlayer(playerUuid: UUID)
    {
        if (this.members[playerUuid] != null)
        {
            members.remove(playerUuid)
            Server.clans().clanMembers.remove(playerUuid)
        }
    }

    fun getOnlineMembers(): List<ClanMember>
    {
        return members.values.stream().filter(ClanMember::isOnline).collect(Collectors.toList())
    }

    fun rankup(): Boolean
    {
        return false
    }

    fun dissolve()
    {

    }

    fun apply(player: Player): Boolean
    {
        if (player.clan != null)
        {
            player.sendMessage(Components.fromLegacy("&c错误! &f你已经加入一个公会了"))
            return false
        }

        when (this.joinPolicy)
        {
            ClanJoinPolicy.FREE                -> join(player.uuid)
            ClanJoinPolicy.CENSORSHIP_REQUIRED ->
            {
                this.applies.add(player.uuid)
                val msg: Component = Components.fromLegacy("&a公会 &6>> &f玩家 ${Server.getCustomName(player)} &f申请加入你的公会")
                members.values.stream()
                        .filter { (it.isOnline) && (it.level.level >= 3) }
                        .map { Minestom.getPlayer(it.playerUuid)!! }
                        .forEach { it.sendMessage(msg) }
            }
            ClanJoinPolicy.CONDITION_REQUIRED  ->
            {

            }
            ClanJoinPolicy.NOT_ACCEPT          ->
            {
                player.sendMessage("&c错误! &f这个公会暂时不接受任何申请")
                return false
            }
        }
        return true
    }

    fun removeInvitedMember(uuid: UUID) {
        this.invitedMembers.remove(uuid)
    }

    fun isAllyWith(clan: Clan): Boolean = allies.contains(clan.uid)

    fun acceptAllyRequest(clan: Clan)
    {

    }

    fun sendAllyRequest(clan: Clan): Boolean
    {
        return false
    }

    fun inviteMember(player: Player): Boolean
    {
        if (checkIfInvited(player.uuid)) return false
        else
        {
            this.invitedMembers.add(player.uuid)
            player.sendMessage("&a公会 &6&l>> &f你被邀请加入公会 &e${this.name}")
            return true
        }
    }

    fun checkIfInvited(playerUuid: UUID): Boolean = this.invitedMembers.contains(playerUuid) || members.containsKey(
            playerUuid)

    fun isMaster(player: Player): Boolean = Objects.equals(player.uuid, this.clanMaster)

    fun broadcast(text: String)
    {
        broadcast(Components.fromLegacy(text))
    }

    fun broadcast(text: Component)
    {
        val msg = Components.fromLegacy("&a公会 &6&l>> ")
                .append(text)
        getOnlineMembers().stream()
                .forEach { it.player!!.sendMessage(msg) }
    }

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()

        val jManifest = JsonArray()
        val jPlayers = JsonArray()
        val jRegions = JsonArray()
        this.motd.forEach { string -> jManifest.add(string) }
        this.members.forEach { uuid -> jPlayers.add(uuid.toString()) }
        //this.regions.forEach { region -> jRegions.add(region.uid.toString()) }

        jsonObject.addProperty("name", this.name)
        jsonObject.add("manifest", jManifest)
        jsonObject.addProperty("leader", this.clanMaster.toString())
        jsonObject.addProperty("tier", this.tier.level)
        jsonObject.addProperty("bank", this.bank.money)
        jsonObject.add("players", jPlayers)
        jsonObject.add("regions", jRegions)

        return jsonObject
    }
}