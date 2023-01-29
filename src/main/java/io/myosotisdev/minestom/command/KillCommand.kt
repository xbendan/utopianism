package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
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
        addSyntax({ sender, context ->
            (sender as Player).kill()
        })
        addSyntax({ sender, context ->
            val player = context.get(arg0_player)
                    .findFirstPlayer(sender)
            if (player != null)
            {
                player.kill()
                sender.sendMessage(player.name.append(Component.text(" has been killed.")))
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