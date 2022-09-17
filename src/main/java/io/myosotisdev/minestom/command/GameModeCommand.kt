package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.Minestom.checkPermission
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player

class GameModeCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        const val Name = "gamemode"
        val Aliases = arrayOf(
                "gm"
        )
    }
    
    private val arg0_mode: ArgumentEnum<GameMode> = ArgumentType.Enum("gamemode", GameMode::class.java)
    private val arg1_player: ArgumentEntity = ArgumentType.Entity("player")
            .onlyPlayers(true)
            .singleEntity(true)

    init
    {
        addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->
            val newMode = context.get(arg0_mode)
            (sender as Player).setGameMode(newMode)
            sender.sendMessage("Your gamemode has been set to " + newMode.name)
        }, arg0_mode)

        addConditionalSyntax(Commands.withPerm(permission(), true), CommandExecutor { sender, context ->
            val player = context.get(arg1_player)
                    .findFirstPlayer(sender)
            if (player != null)
            {
                val newMode = context.get(arg0_mode)
                player.setGameMode(newMode)
                player.sendMessage("Your gamemode has been set to " + newMode.name)
            }
            else sender.sendMessage(Component.text("Player does not exist.", TextColor.color(255, 0, 0)))
        }, arg0_mode, arg1_player)
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minecraft, "gamemode")
    }
}