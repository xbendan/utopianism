package io.myosotisdev.minestom.command.manage

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.entity.Player

class WorldSubCommand(command: AbstractCommand?) : AbstractCommand(command, Name)
{
    companion object
    {
        const val Name = "world"
        val Aliases = arrayOfNulls<String>(0)
    }

    init
    {
        addSyntax({ sender, context ->
            (sender as? Player)?.sendMessage(sender.instance.toString())
        })
    }

    override fun permission(): Permission?
    {
        return Permission.ofString(Namespaces.Minecraft, Name)
    }
}