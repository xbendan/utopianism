package io.myosotisdev.minestom

import io.myosotisdev.minestom.entity.PlayerEx
import net.minestom.server.entity.Player
import net.minestom.server.network.PlayerProvider
import net.minestom.server.network.player.PlayerConnection
import java.util.*

class PlayerProviderEx : PlayerProvider
{
    override fun createPlayer(uuid: UUID, username: String, connection: PlayerConnection): Player
    {
        return PlayerEx(uuid, username, connection)
    }
}