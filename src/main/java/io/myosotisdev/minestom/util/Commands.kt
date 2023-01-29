package io.myosotisdev.minestom.util

import net.minestom.server.command.builder.Command

object Commands
{
    fun usages(command: Command): List<String>
    {
        val usageList: MutableList<String> = ArrayList()
        command.subcommands.stream()
                .forEach { subcommand ->
                    usages(subcommand as Command).forEach { string -> usageList.add("${command.name} $string") }
                }
        command.syntaxes.stream()
                .forEach { syntax ->
                    var syntaxContent = command.name
                    for (arg in syntax.arguments)
                        syntaxContent += " <${arg.id}>"
                    usageList.add(syntaxContent)
                }
        return usageList
    }
}