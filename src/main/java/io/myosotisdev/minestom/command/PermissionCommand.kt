package io.myosotisdev.minestom.command

import com.google.common.base.Strings
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.module.PermissionModule
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.minestom.permission.PermissionGroup
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentGroup
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.ArgumentWord
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import net.minestom.server.entity.Player

/**
 * command 'grant' is used to grant permission to a specific unit that can hold permissions, such as Player, but also
 * any created permission groups.
 *
 * @author RainbowMeowCat
 */
class PermissionCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        const val Name = "permission"
        val Aliases = arrayOf(
                "perm")
        val arg_operation: ArgumentWord = ArgumentType.Word("operation")
                .from("add", "remove")
        val arg_handlerType: ArgumentWord = ArgumentType.Word("type")
                .from("player", "group")
        val arg_name: ArgumentString = ArgumentString("name")
        val arg_name0: ArgumentWord = ArgumentType.Word("name")
                .from(*Minestom.getOnlinePlayers()
                        .map { player: Player -> player.name.examinableName() }
                        .toTypedArray(),
                        *(Minestom.getModule("permission") as PermissionModule).groups.map { group: PermissionGroup -> group.name }
                                .toTypedArray())
        val arg_permission: ArgumentString = ArgumentType.String("permission");
    }

    init
    {
        //addSubcommand(Add(this))
        //addSubcommand(Remove(this))

        arg_name.setSuggestionCallback { sender, context, suggestion ->
            var nameList: Array<String> = arrayOf(*Minestom.getOnlinePlayers()
                    .map { player: Player -> player.name.examinableName() }
                    .toTypedArray(), *(Minestom.getModule("permission") as PermissionModule).groups.map { group: PermissionGroup -> group.name }
                    .toTypedArray())

            nameList.forEach { string ->
                if (Strings.isNullOrEmpty(suggestion.input) || string.lowercase ()
                                .startsWith(suggestion.input.lowercase())) suggestion.addEntry(SuggestionEntry(string))
            }
        }

        addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->

        }, arg_handlerType, arg_name, arg_operation, arg_permission)
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minestom, "permission")
    }

    internal class Add(parent: AbstractCommand?) : AbstractCommand(parent, Name)
    {
        companion object
        {
            const val Name: String = "add"
        }

        init
        {
            addConditionalSyntax(Commands.withPerm(permission(), true), { sender, context ->

            }, arg_handlerType, arg_name, arg_permission)
        }

        override fun permission(): Permission?
        {
            return parent?.permission()
        }
    }

    internal class Remove(parent: AbstractCommand?) : AbstractCommand(parent, Name)
    {
        companion object
        {
            const val Name: String = "remove"
        }

        init
        {
            addConditionalSyntax(Commands.withPerm(permission(), true), CommandExecutor { sender, context ->

            }, arg_handlerType, arg_name, arg_permission)
        }

        override fun permission(): Permission?
        {
            return parent?.permission()
        }
    }
}