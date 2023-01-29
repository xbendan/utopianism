package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.team.Team
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity
import net.minestom.server.command.builder.arguments.number.ArgumentInteger
import net.minestom.server.entity.Player
import java.util.*

class TeamCommand : AbstractCommand
{
    companion object
    {
        val Name: String = "team"
    }

    constructor() : super(null, Name)
    {
        addSubcommand(Create(this))
        addSubcommand(Leave(this))
        addSubcommand(Dissolve(this))
        addSubcommand(Invite(this))
        addSubcommand(Join(this))
    }

    override fun permission(): Permission
    {
        return Permission.ofString(Namespaces.Utopianism, "team")
    }

    class Create(command: AbstractCommand) : AbstractCommand(command, "create")
    {
        val arg0_name: ArgumentString = ArgumentType.String("name")

        init
        {
            addSyntax({ sender, context ->
                var name: String = context[arg0_name]
                Server.teams().createTeam(sender as Player)
            })
        }

        override fun permission(): Permission? = parent?.permission()
    }

    class Kick(command: AbstractCommand) : AbstractCommand(command, "kick")
    {
        val arg0_playerName: ArgumentEntity = ArgumentType.Entity("player")
                .singleEntity(true)
                .onlyPlayers(true)

        init
        {
            addSyntax({ sender, context ->
                val player = context[arg0_playerName].findFirstEntity(sender) as Player?
                if (Objects.isNull(player))
                {
                    sender.sendMessage("&c玩家不存在")
                    return@addSyntax
                }
                Server.teams().kickPlayer(sender as Player, player!!)
            }, arg0_playerName)
        }

        override fun permission(): Permission = Permission.ofString(Namespaces.Utopianism, "party.kick")
    }

    class Leave(command: AbstractCommand) : AbstractCommand(command, "leave")
    {
        init
        {
            addSyntax({ sender, context ->
                val player: Player = sender as Player
                val team = player.getTag(Team.TeamTag)

                if (Objects.isNull(team))
                {
                    player.sendMessage("&c错误! &f你尚未加入任何队伍")
                    return@addSyntax
                }

                team.leave(player)
            })
        }

        override fun permission(): Permission?
        {
            return parent?.permission()
        }
    }

    class Invite(command: AbstractCommand) : AbstractCommand(command, "invite")
    {
        val arg0_playerName: ArgumentEntity = ArgumentType.Entity("player")
                .onlyPlayers(true)

        init
        {
            addSyntax({ sender, context ->
                val player = context[arg0_playerName].findFirstEntity(sender) as Player?
                if (Objects.isNull(player))
                {
                    sender.sendMessage("&c玩家不存在")
                    return@addSyntax
                }
                Server.teams().invitePlayer(sender as Player, player!!)
            }, arg0_playerName)
        }

        override fun permission(): Permission
        {
            return Permission.ofString(Namespaces.Utopianism, "party.invite")
        }
    }

    class Join(command: AbstractCommand) : AbstractCommand(command, "join")
    {
        val arg0_partyCode: ArgumentInteger = ArgumentType.Integer("code")

        init
        {

        }

        override fun permission(): Permission? = parent!!.permission()
    }

    class Accept(command: AbstractCommand) : AbstractCommand(command, "accept")
    {
        val arg0_leaderName = ArgumentType.Entity("leader-name")
                .onlyPlayers(true)
                .singleEntity(true)
        val arg0_teamCode = ArgumentType.Integer("team-code")

        init
        {
            addSyntax({ sender, _ -> Server.teams().inviteAccept(sender as Player) })
            addSyntax({ sender, context ->

            }, arg0_leaderName)
            addSyntax({ sender, context ->
                val code = context[arg0_teamCode]
                if (code in 10000000..99999999)
                {
                    val team = Server.teams().findByCode(code)
                    if (Objects.isNull(team))
                        sender.sendMessage("&c没有符合邀请码的队伍，可能该队伍已经解散了")
                    else
                        Server.teams().inviteAccept(sender as Player, team)
                }
                else sender.sendMessage("&c邀请码超出范围")
            }, arg0_teamCode)
        }

        override fun permission(): Permission? = parent?.permission()
    }

    class Dissolve(command: AbstractCommand) : AbstractCommand(command, "dissolve")
    {
        init
        {
            addSyntax({ sender, _ -> Server.teams().dissolveTeam(sender as Player) })
        }

        override fun permission(): Permission? = parent?.permission()
    }
}