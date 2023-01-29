package io.myosotisdev.utopianism.modules.player

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.minestom.entity.MinestomPlayer
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.model.Economy
import io.myosotisdev.utopianism.modules.Exp
import io.myosotisdev.utopianism.modules.entitydata.PlayerData
import io.myosotisdev.utopianism.modules.clan.Clan
import io.myosotisdev.utopianism.modules.clan.ClanMember
import io.myosotisdev.utopianism.modules.homeland.Homeland
import io.myosotisdev.utopianism.modules.mailbox.Mailbox
import io.myosotisdev.utopianism.modules.task.ITaskExecutor
import io.myosotisdev.utopianism.modules.teleport.AnchorPoint
import io.myosotisdev.utopianism.modules.teleport.ITeleportable
import io.myosotisdev.utopianism.util.JsonObjects
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.network.player.PlayerConnection
import java.io.File
import java.util.*

class PlayerEx : MinestomPlayer,
                 ITaskExecutor,
                 IJsonData,
                 ITeleportable
{
    override val teleportCooldowns: HashMap<AnchorPoint, Long> = HashMap()
    val data: PlayerData
    var health: Double
        get() = data.health.actualHealth
        set(value)
        {
            data.health.actualHealth = value
        }

    constructor(uuid: UUID, username: String, playerConnection: PlayerConnection) : super(uuid, username, playerConnection)
    {
        val profile = File("profiles/$uuid.json")
        data = if (profile.exists()) PlayerData(this, JsonObjects.readFile(profile)!!.asJsonObject) else PlayerData(this)

    }

    fun getExpertExperience(skillExpert: SkillExpert): Exp
    {
        return data.experts[skillExpert]!!
    }

    fun getExpertLevel(expert: SkillExpert): Int
    {
        return data.getExpertLevel(expert)
    }

    override fun getCustomName(): Component = Components.fromLegacy(Server.getCustomName(uuid))

    override fun sendMessage(message: String)
    {
        super.sendMessage(Components.fromLegacy(message))
    }

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()

        jsonObject.addProperty("uuid", uuid.toString())
        jsonObject.add("location", JsonObjects.asJsonCopy(position, instance))
        jsonObject.add("data", data.asJsonCopy())

        return jsonObject
    }
}

val Player.data: PlayerData
    get() = (this as PlayerEx).data

val Player.clanMember: ClanMember?
    get() = clan?.members?.get(this.uuid)

val Player.clan: Clan?
    get() = Server.clans().findByPlayer(this.uuid)

val Player.economy: Economy
    get() = Server.getEconomy(this.uuid)!!

var Player.money: Double
    get() = economy.money
    set(value)
    {
        economy.money = money
    }

val Player.homeland: Homeland?
    get() = Server.homelands().landMapping[uuid]