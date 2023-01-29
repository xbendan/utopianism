package io.myosotisdev.minestom.module

import com.google.gson.JsonParser
import io.myosotisdev.minestom.util.Components
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent
import java.io.File
import java.io.FileReader
import java.util.*
import java.util.function.Consumer

class BanPlayersModule : ModuleManager("ban")
{
    val bannedPlayers: HashSet<BanRecord> = HashSet()

    override fun onEnable()
    {
        val file = File("banned.json")
        if (file.exists())
        {
            val jsonArray = JsonParser.parseReader(FileReader(file)).asJsonArray
            jsonArray.forEach { jobj ->
                val jObj = jobj.asJsonObject
                bannedPlayers.add(BanRecord(UUID.fromString(jObj["uuid"].asString),
                        jObj["time"].asLong,
                        jObj["reason"].asString))
            }
        }

        addListener(AsyncPlayerPreLoginEvent::class.java, Consumer { event ->
            val bannedPlayerRecord = getBannedPlayerRecord(event.playerUuid)
            if (Objects.nonNull(bannedPlayerRecord)) event.player.kick(Component.text("账户已封禁", NamedTextColor.RED)
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.newline())
                    .append(Components.fromLegacy(if (bannedPlayerRecord!!.time < 0) "&4永久封禁"
                    else "&f解封时间：&e${
                        Date(bannedPlayerRecord.time)
                    }"))
                    .append(Component.newline())
                    .append(Components.fromLegacy(bannedPlayerRecord.reason)))
        })
    }

    override fun onDisable()
    {

    }

    fun isBanned(uuid: UUID): Boolean = bannedPlayers.any { record -> record.uuid == uuid }

    fun getBannedPlayerRecord(uuid: UUID): BanRecord? = bannedPlayers.filter { record -> record.uuid == uuid }
            .firstOrNull()

    class BanRecord(val uuid: UUID, val time: Long, val reason: String)
}