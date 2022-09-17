package io.myosotisdev.minestom.command.manage

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player

class OperatorSubCommand(parent: AbstractCommand?) : AbstractCommand(parent, Name, *Aliases)
{
    private val arg0_playerName = ArgumentType.String("name")
    private val arg1_enable = ArgumentType.Boolean("enable")

    init
    {
        addSyntax({ sender: CommandSender, context: CommandContext ->
            if (!Minestom.checkPermission(sender, permission())) sender.sendMessage("You have no permission to do that!")
            else
            {
                if (sender is Player) Minestom.setOp(sender, context.get(arg1_enable)) else sender.sendMessage("This command could only be used by player.")
            }
        }, arg1_enable)
        addSyntax({ sender: CommandSender, context: CommandContext ->
            if (!Minestom.checkPermission(sender, permission())) sender.sendMessage("You have no permission to do that!")
            else
            {
                val player = Minestom.getPlayer(context.get(arg0_playerName))
                if (player != null)
                {
                    val opEnable = context.get(arg1_enable)
                    Minestom.setOp(player, opEnable)
                    sender.sendMessage(Component.textOfChildren(player.name, Component.text(if (opEnable) " has been set to operator." else " is not operator any more.")))
                }
                else sender.sendMessage("Player does not exist.")
            }
        }, arg0_playerName, arg1_enable)
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