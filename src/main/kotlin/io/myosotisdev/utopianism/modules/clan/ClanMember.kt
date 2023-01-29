package io.myosotisdev.utopianism.modules.clan

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.minestom.Minestom
import net.minestom.server.entity.Player
import java.util.*

class ClanMember : IJsonData
{
    val clan: Clan
    val playerUuid: UUID
    var level: ClanClass
    var contribution: Int
    var joinTime: Long
    var lastLogin: Long

    val perms: EnumMap<ClanRolePerm, Boolean> = EnumMap(ClanRolePerm::class.java)
    var player: Player? = null
    val isOnline: Boolean
        get() = Objects.nonNull(player) || Minestom.getPlayer(playerUuid) != null

    constructor(clan: Clan, playerUuid: UUID, level: ClanClass = ClanClass.NORMAL_MEMBER)
    {
        this.playerUuid = playerUuid
        this.clan = clan
        this.level = level
        this.contribution = 0
        this.joinTime = System.currentTimeMillis()
        this.lastLogin = if (Minestom.getPlayer(playerUuid) != null) -1 else 0

        setLevelPerm(this.level)
    }

    constructor(clan: Clan, jsonObject: JsonObject)
    {
        this.clan = clan
        this.playerUuid = UUID.fromString(jsonObject["uuid"].asString)
        this.level = ClanClass.valueOf(jsonObject["level"].asString.uppercase())
        this.contribution = jsonObject["contribution"].asInt
        this.joinTime = jsonObject["joinTime"].asLong
        this.lastLogin = if (Minestom.getPlayer(playerUuid) != null) -1 else jsonObject["lastLogin"].asLong

        ClanRolePerm.values()
                .forEach { perms[it] = jsonObject["policies"].asJsonObject[it.name]?.asBoolean ?: false }
    }

    fun setLevelPerm(level: ClanClass)
    {
        ClanRolePerm.values().forEach { perms[it] = level.rolePerms.contains(it) }
    }

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()

        jsonObject.addProperty("level", level.toString())
        jsonObject.addProperty("uuid", playerUuid.toString())
        jsonObject.addProperty("contribution", contribution)
        jsonObject.addProperty("joinTime", joinTime)
        jsonObject.addProperty("clan", clan.uid.toString())

        return jsonObject
    }

    fun leave()
    {
        if (Objects.equals(clan.clanMaster, playerUuid))
        {
            player?.sendMessage("&c错误! &f你不能离开公会，因为你是会长")
            return
        }

        clan.removePlayer(playerUuid)
    }
}