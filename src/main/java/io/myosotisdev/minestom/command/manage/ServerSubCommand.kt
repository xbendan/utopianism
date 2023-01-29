package io.myosotisdev.minestom.command.manage

import io.myosotisdev.minestom.Minestom.stop
import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.utopianism.util.Namespaces

class ServerSubCommand(parent: AbstractCommand?) : AbstractCommand(parent, Name)
{
    companion object
    {
        const val Name = "server"
        val Aliases = arrayOfNulls<String>(0)
    }

    init
    {
        addSubcommand(Stop(this))
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minestom, "manage.server")
    }

    internal class Stop(command: AbstractCommand?) : AbstractCommand(command, Name)
    {
        companion object { const val Name = "stop" }

        init
        {
            addSyntax({ sender, context ->
                stop()
            })
        }

        override fun permission(): Permission?
        {
            return parent!!.permission()
        }
    }
}