package io.myosotisdev.utopianism.modules.player

import net.minestom.server.entity.Player
import net.minestom.server.network.PlayerProvider
import net.minestom.server.network.player.PlayerConnection
import java.util.*

class MsPlayerProvider : PlayerProvider
{
    override fun createPlayer(uuid: UUID, username: String, connection: PlayerConnection): Player
    {
        return MsPlayer(uuid, username, connection)
    }
}