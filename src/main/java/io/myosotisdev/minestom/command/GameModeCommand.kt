package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.ArgumentWord
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity
import net.minestom.server.command.builder.arguments.number.ArgumentInteger
import net.minestom.server.command.builder.arguments.number.ArgumentNumber
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

    val arg0_mode: ArgumentWord = ArgumentType.Word("gamemode")
            .from(*GameMode.values()
                    .asList()
                    .map { gameMode ->
                        gameMode.toString()
                                .lowercase()
                    }
                    .toTypedArray())
    val arg0_modeInt: ArgumentNumber<Int> = ArgumentInteger("gamemode").between(0, 3);
    val arg1_player: ArgumentEntity = ArgumentType.Entity("player")
            .onlyPlayers(true)

    init
    {
        addSyntax({ sender, context ->
            val newMode = GameMode.valueOf(context.get(arg0_mode).uppercase())
            (sender as Player).gameMode = newMode
            sender.sendMessage("Your gamemode has been set to " + newMode.name)
        }, arg0_mode)

        addSyntax({ sender, context ->
            val player = context.get(arg1_player)
                    .findFirstPlayer(sender)
            if (player != null)
            {
                val newMode = GameMode.valueOf(context.get(arg0_mode).uppercase())
                player.gameMode = newMode
                player.sendMessage("Your gamemode has been set to " + newMode.name)
            }
            else sender.sendMessage(Components.fromLegacy("&cPlayer does not exist."))
        }, arg0_mode, arg1_player)

        addSyntax({ sender, context ->
            val newMode = GameMode.fromId(context.get(arg0_modeInt))
            (sender as Player).gameMode = newMode
            sender.sendMessage("Your gamemode has been set to " + newMode.name)
        }, arg0_modeInt)

        addSyntax({ sender, context ->
            val player = context.get(arg1_player)
                    .findFirstPlayer(sender)
            if (player != null)
            {
                val newMode = GameMode.fromId(context.get(arg0_modeInt))
                player.gameMode = newMode
                player.sendMessage("Your gamemode has been set to " + newMode.name)
            }
            else sender.sendMessage(Components.fromLegacy("&cPlayer does not exist."))
        }, arg0_modeInt, arg1_player)
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minecraft, "gamemode")
    }
}