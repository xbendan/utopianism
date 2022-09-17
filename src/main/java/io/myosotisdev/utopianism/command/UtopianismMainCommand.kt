package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.CommandExecutor

class UtopianismMainCommand : AbstractCommand {
    companion object {
        val Name: String = "utopianism"
        val Aliases: Array<String> = arrayOf(
                "ut"
        );
    }


    constructor() : super(null, Name, *Aliases) {
        addConditionalSyntax(Commands.withPerm(permission()), (CommandExecutor { sender, context ->

        }));

        addSubcommand(GuildCommand(this));
    }

    override fun permission(): Permission {
        return Permission.ofString(Namespaces.Utopianism, "main")
    }
}