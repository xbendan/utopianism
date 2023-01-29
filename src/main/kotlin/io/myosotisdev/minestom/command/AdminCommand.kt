package io.myosotisdev.minestom.command

import io.myosotisdev.minestom.command.admin.BackpackCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.utopianism.util.Namespaces

open class AdminCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        const val Name = "administrator"
        val Aliases = arrayOf(
                "admin")
    }

    init
    {
        addSubcommand(BackpackCommand(this))
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minestom, "admin")
    }
}