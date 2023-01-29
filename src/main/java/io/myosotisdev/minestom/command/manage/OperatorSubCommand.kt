package io.myosotisdev.minestom.command.manage

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player

class OperatorSubCommand(parent: AbstractCommand?) : AbstractCommand(parent, Name, *Aliases)
{
    private val arg1_playerName = ArgumentType.Entity("name").onlyPlayers(true)
    private val arg0_enable = ArgumentType.Boolean("enable")

    init
    {
        addSyntax({ sender, context ->
            Minestom.setOp(sender as Player, context[arg0_enable])
        }, true, arg0_enable)

        addSyntax({ sender, context ->
            val player = context.get(arg1_playerName).findFirstPlayer(sender)
            if (player != null)
            {
                val opEnable = context.get(arg0_enable)
                Minestom.setOp(player, opEnable)
                sender.sendMessage(Components.asLegacyCopy(player.name) + if (opEnable) " has been set to operator." else " is not operator any more.")
            }
            else sender.sendMessage("Player does not exist.")
        }, true, arg0_enable, arg1_playerName)
    }

    override fun permission(): Permission?
    {
        return Permission.ofString(Namespaces.Minestom, "manage.operator")
    }

    companion object
    {
        const val Name = "operator"
        val Aliases = arrayOf(
                "op",
                "administrator",
                "admin"
        )
    }
}