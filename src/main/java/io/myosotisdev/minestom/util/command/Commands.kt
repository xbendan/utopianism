package io.myosotisdev.minestom.util.command

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.condition.CommandCondition

object Commands
{
    @JvmOverloads
    fun withPerm(permission: Permission?, allowConsole: Boolean = false): CommandCondition
    {
        return CommandCondition { sender: CommandSender, _: String? -> (sender !is ConsoleSender || allowConsole) && Minestom.checkPermission(sender, permission) }
    }

    fun usages(command: AbstractCommand): List<String>
    {
        var usageList: MutableList<String> = ArrayList()
        command.subcommands.stream()
                .forEach { subcommand ->
                    usages(subcommand as AbstractCommand).forEach { string -> usageList.add("${command.name} $string") }
                }
        command.syntaxes.stream()
                .forEach { syntax ->
                    var syntaxContent = "${command.name}"
                    for (arg in syntax.arguments)
                        syntaxContent += " <${arg.id}>"
                    usageList.add(syntaxContent)
                }
        return usageList
    }
}