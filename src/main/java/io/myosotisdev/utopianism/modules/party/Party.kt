package io.myosotisdev.utopianism.modules.party

import com.google.common.base.Strings
import io.myosotisdev.utopianism.modules.dungeon.Dungeon
import io.myosotisdev.utopianism.modules.task.ITaskExecutor
import net.minestom.server.entity.Player
import java.util.*

class Party(private val uuid: UUID, teamName: String?, player: Player) : ITaskExecutor
{
    private val name: String
    val players: Set<Player>
    private val leader: Player
    private val friendlyFire = false
    private val playing: Dungeon? = null

    init
    {
        name = if (Strings.isNullOrEmpty(teamName)) uuid.toString() else teamName!!
        players = HashSet()
        leader = player
    }

    fun join(player: Player?)
    {
    }

    fun kick(player: Player?)
    {
    }

    fun invite(player: Player?)
    {
    }

    fun setLeader(player: Player?)
    {
    }

    fun getLeader(): Player
    {
        return leader
    }

    val onlines: List<Player>
        get()
        {
            val onlineList: MutableList<Player> = ArrayList()
            players.stream()
                    .forEach { player: Player -> if (player.isOnline) onlineList.add(player) }
            return onlineList
        }

    fun convene(sender: Player?)
    {
    }

    fun convene(sender: Player?, player: Player?)
    {
    }

    fun broadcast(message: String?)
    {
    }
}