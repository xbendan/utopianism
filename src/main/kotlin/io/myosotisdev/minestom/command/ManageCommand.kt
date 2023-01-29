package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.command.manage.OperatorSubCommand
import io.myosotisdev.minestom.command.manage.ServerSubCommand
import io.myosotisdev.minestom.command.manage.WorldSubCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces

class ManageCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        const val Name = "manage"
        val Aliases = arrayOf(
                "man",
                "mg"
        )
    }

    init
    {
        addSubcommand(OperatorSubCommand(this))
        addSubcommand(ServerSubCommand(this))
        addSubcommand(WorldSubCommand(this))
    }

    override fun permission(): Permission?
    {
        return Permission.ofString(Namespaces.Minestom, Name)
    }
}