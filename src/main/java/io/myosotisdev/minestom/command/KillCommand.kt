package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.minestom.util.component.PrebuildComponentText
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player

class KillCommand : AbstractCommand(null, Name)
{
    companion object
    {
        const val Name = "kill"
        val Aliases = arrayOfNulls<String>(0)
    }

    private val arg0_player = ArgumentType.Entity("player")
            .onlyPlayers(true)
            .singleEntity(true)

    init
    {
        addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->
            (sender as Player).kill()
        })
        addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->
            val player = context.get(arg0_player)
                    .findFirstPlayer(sender)
            if (player != null)
            {
                player.kill()
                sender.sendMessage(Component.textOfChildren(
                        player.name,
                        Component.text(" has been killed.")
                ))
            }
            else
                sender.sendMessage("Player does not exist.")
        }, arg0_player)
    }

    override fun permission(): Permission?
    {
        return Permission.ofString(Namespaces.Minecraft, "kill")
    }
}