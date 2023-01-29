package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.Commands
import io.myosotisdev.minestom.util.Components
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax
import net.minestom.server.command.builder.arguments.Argument

abstract class AbstractCommand(val parent: AbstractCommand?, name: String, vararg aliases: String?) : Command(name, *aliases)
{

    init
    {
        defaultExecutor = CommandExecutor { sender: CommandSender, context: CommandContext? ->
            if(Minestom.checkPermission(sender, permission()))
            {
                showUsages(sender)
            }
            else
                sender.sendMessage(Components.fromLegacy("&4You don't have enough permission to do that!"))
        }
    }

    fun addSyntax(executor: CommandExecutor, permission: Permission? = permission(), allowConsole: Boolean = false, vararg args: Argument<*>): MutableCollection<CommandSyntax>
    {
        return super.addSyntax({ sender, context ->
            if(sender is ConsoleSender && !allowConsole)
            {
                sender.sendMessage(Components.fromLegacy("&4This command could only be used by a player."))
                return@addSyntax
            }

            if(!Minestom.checkPermission(sender, permission))
            {
                sender.sendMessage(Components.fromLegacy("&4You don't have enough permission to do that!"))
                return@addSyntax
            }

            executor.apply(sender, context)
        }, *args)
    }

    fun addSyntax(executor: CommandExecutor, allowConsole: Boolean, vararg args: Argument<*>): MutableCollection<CommandSyntax>
    {
        return addSyntax(executor, permission(), allowConsole, *args)
    }

    override fun addSyntax(executor: CommandExecutor, vararg args: Argument<*>): MutableCollection<CommandSyntax>
    {
        return addSyntax(executor, permission(), false, *args)
    }

    fun showUsages(sender: CommandSender)
    {
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

    abstract fun permission(): Permission?
}