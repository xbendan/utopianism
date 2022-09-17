package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.minestom.util.component.Components
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax
import java.util.function.Consumer

abstract class AbstractCommand(val parent: AbstractCommand?, name: String, vararg aliases: String?) : Command(name, *aliases)
{

    init
    {
        defaultExecutor = CommandExecutor { sender: CommandSender, context: CommandContext? ->
            sender.sendMessage(Components.fromLegacy("&aCommand: &8[&6" + getName() + "&8]"))
            sender.sendMessage(Components.fromLegacy("&7Aliases:"))
            getAliases()?.forEach { alias ->
                sender.sendMessage(Components.fromLegacy("&7 - &6$alias"))
            }
            sender.sendMessage(Components.fromLegacy("&7Syntaxes:"))
            Commands.usages(this).stream().forEach { string ->
                sender.sendMessage(Components.fromLegacy("&7 - &f" + string))
            }
        }
    }

    abstract fun permission(): Permission?
}