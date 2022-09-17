package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.arguments.ArgumentType

class MailboxCommand(command: AbstractCommand?) : AbstractCommand(command, Name, *Aliases)
{
    companion object
    {
        val Name: String = "mailbox"
        val Aliases: Array<String> = arrayOf(
                "mail"
        )
    }

    init
    {

    }

    override fun permission(): Permission?
    {
        return Permission.ofString(Namespaces.Utopianism, "mailbox")
    }
}