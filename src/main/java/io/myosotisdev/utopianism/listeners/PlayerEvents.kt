package io.myosotisdev.utopianism.listeners

import com.google.gson.Gson
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.chat.PlayerDisplayName
import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.ui.generic.VisualPlayerData
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerStartSneakingEvent
import java.io.File
import java.io.FileWriter

class PlayerEvents
{
    val gson = Gson()

    init
    {
        Minestom.registerListener(PlayerStartSneakingEvent::class.java) { event ->
            val player = event.player as PlayerEx
            if (player.position.pitch == -90.0f)
            {
                player.openInventory(VisualPlayerData(player))
            }
        }
        Minestom.registerListener(AsyncPlayerPreLoginEvent::class.java) { event ->
            val player = event.player
            val uuid = event.playerUuid

            Server.getChatManager().displayNameRecords[uuid] = (Server.getChatManager().loadPlayer(uuid) ?: PlayerDisplayName(player))
        }
        Minestom.registerListener(PlayerDisconnectEvent::class.java) { event ->
            val player = event.player as PlayerEx
            var file = File("profiles/${player.uuid}.json")

            if (file.exists()) file.delete()
            file.createNewFile()
            gson.toJson(player.asJsonCopy(), FileWriter(file))
        }
    }
}