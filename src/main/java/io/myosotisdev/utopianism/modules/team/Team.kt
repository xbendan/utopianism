package io.myosotisdev.utopianism.modules.team

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.dungeon.Dungeon
import io.myosotisdev.utopianism.modules.task.ITaskExecutor
import io.myosotisdev.utopianism.util.Tags
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import java.util.*
import kotlin.collections.HashSet

class Team(val uuid: UUID, player: Player, val inviteCode: Int) : ITaskExecutor
{
    companion object {
        val Serializer = TeamTagSerializer()
        val TeamTag = Tags.createTag("team", Serializer)
        val InviteTag = Tags.createTag("team-invite", Serializer)
    }

    val players: HashSet<Player> = HashSet()
    val invites: HashSet<Player> = HashSet()
    var leader: Player
    var friendlyFire = false
    var playing: Dungeon? = null

    init
    {
        leader = player
        players.add(player)
        player.setTag(TeamTag, this)
    }

    fun join(player: Player): Boolean
    {
        val team = player.getTag(TeamTag)
        if (team != null)
        {
            if (Objects.equals(team.leader, player))
                return false

            team.remove(player)
        }

        this.players.add(player)
        this.invites.remove(player)
        broadcast("&f玩家 &e${Server.getCustomName(player)} &f加入了队伍")

        return true
    }

    fun add(player: Player)
    {
        this.players.add(player)
        player.setTag(TeamTag, this)
        player.setTag(Team.InviteTag, null)
    }

    fun remove(player: Player)
    {
        this.players.remove(player)
        player.setTag(TeamTag, null)
    }

    fun convene(sender: Player?)
    {
    }

    fun convene(sender: Player?, player: Player?)
    {
    }

    fun leave(player: Player)
    {
        if(players.remove(player))
        {
            broadcast("&a玩家 &e${Server.getCustomName(player)} &a离开了组队")
            player.setTag(TeamTag, null)
        }
    }

    fun broadcast(message: String)
    {
        broadcast(Components.fromLegacy(message))
    }

    fun broadcast(message: Component)
    {
        players.stream().forEach { it.sendMessage(message) }
    }

    fun dissolve()
    {
        broadcast("&a队伍已解散!")
        players.stream().forEach { it.setTag(TeamTag, null) }
        Server.teams().removeTeam(this)
    }

}