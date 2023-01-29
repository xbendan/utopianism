package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.command.TeamCommand
import io.myosotisdev.utopianism.modules.team.Team
import net.minestom.server.entity.Player
import java.util.*

class TeamManager : ModuleManager("party")
{
    private val teams: HashSet<Team> = HashSet()
    val teamCodes: HashMap<Int, Team> = HashMap()
    val rand = Random()

    override fun onEnable()
    {
        addCommand(TeamCommand())
    }

    override fun onDisable()
    {
    }

    fun createTeam(player: Player): Team?
    {
        if (player.getTag(Team.TeamTag) != null)
        {

            return null
        }

        val code = rand.nextInt(89999999) + 10000000
        val team = Team(UUID.randomUUID(), player, code)
        teams.add(team)
        teamCodes[code] = team
        return team
    }

    fun removeTeam(team: Team)
    {
        teamCodes.remove(team.inviteCode)
        teams.remove(team)
    }

    fun findByUuid(uuid: UUID): Team? = teams.stream()
            .filter { party -> party.uuid == uuid }
            .findFirst()
            .orElse(null)

    fun findByPlayer(player: Player): Team? = teams.stream()
            .filter { party -> party.players.contains(player) }
            .findFirst()
            .orElse(null)

    fun findByCode(code: Int): Team? = teamCodes[code]

    fun invitePlayer(inviter: Player, player: Player)
    {
        val team = inviter.getTag(Team.TeamTag)

        if (Objects.isNull(team))
        {
            inviter.sendMessage("&c你必须创建或加入一个队伍才能邀请别人")
            return
        }

        val playerTeam = player.getTag(Team.TeamTag)
        val playerName = Server.getCustomName(player.uuid)
        if (!Objects.equals(team, playerTeam))
        {
            inviter.sendMessage("&a已经向 &f${playerName} &a 发送队伍邀请")
            player.sendMessage("&a你被玩家 &f${Server.getCustomName(inviter.uuid)} &a邀请入队")
            player.setTag(Team.InviteTag, team)
            team.invites.add(player)
        }
        else inviter.sendMessage("&c玩家 &f${playerName} &c已经在你的队伍中")
    }

    fun kickPlayer(op: Player, player: Player)
    {
        val team = player.getTag(Team.TeamTag)
        if (Objects.isNull(team) || Objects.equals(op, team.leader))
        {
            op.sendMessage("&c你不是队伍队长")
            return
        }

        if (Objects.equals(op, player))
        {
            op.sendMessage("&c你不能踢出你自己")
            return
        }

        if (!team.players.contains(player))
        {
            op.sendMessage("&f玩家 &7[&6${Server.getCustomName(player)}&7] &f不在你的队伍之中")
            return
        }

        team.remove(player)
        player.sendMessage("&c你被队长移出队伍")
        op.sendMessage("&a你将 &7[&6${Server.getCustomName(player.uuid)}&7] &a 踢出了队伍.")
    }

    fun inviteAccept(player: Player) = inviteAccept(player, player.getTag(Team.InviteTag))

    fun inviteAccept(player: Player, code: Int) = inviteAccept(player, teamCodes[code])

    fun inviteAccept(player: Player, team: Team?)
    {
        val currentTeam = player.getTag(Team.TeamTag)

        if (!teams.contains(team))
        {
            player.setTag(Team.InviteTag, null)
            player.sendMessage("&c队伍不存在, 可能已经解散")
            return
        }

        if (Objects.equals(currentTeam, team))
        {
            player.setTag(Team.InviteTag, null)
            player.sendMessage("&c你已经在这个队伍中")
            return
        }

        if (Objects.nonNull(currentTeam))
        {
            player.sendMessage("&c你必须退出当前队伍才能加入其它队伍")
            return
        }

        team!!.add(player)
        player.sendMessage("&a你加入了 &f${team.leader.name} &a的队伍")
    }

    fun inviteReject(player: Player)
    {
        val team = player.getTag(Team.InviteTag)

        if (Objects.isNull(team))
        {
            player.sendMessage("&c暂时没有任何队伍邀请你, 或者邀请已过期")
            return
        }

        if (!teams.contains(team))
        {
            player.setTag(Team.InviteTag, null)
            player.sendMessage("&c队伍不存在, 可能已经解散")
            return
        }

        team!!.invites.remove(player)
        team.broadcast("&c玩家 &f${Server.getCustomName(player)} &c拒绝了入队邀请")
    }

    fun leaveTeam(player: Player)
    {

    }

    fun dissolveTeam(player: Player)
    {
        val team = player.getTag(Team.TeamTag)
        if (Objects.isNull(team))
        {
            player.sendMessage("&c你不在任何队伍中")
            return
        }

        if (!Objects.equals(team.leader, player))
        {
            player.sendMessage("&c只有队伍队长才能解散队伍")
            return
        }

        team.dissolve()
    }
}