package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.ui.generic.VisualPlayerData
import io.myosotisdev.utopianism.util.Namespaces

class MenuCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        val Name: String = "menu"
        val Aliases: Array<String> = arrayOf(
                "me",
                "player",
                "p"
        )
    }

    init
    {
        addSyntax({ sender, context ->
            (sender as PlayerEx).openInventory(VisualPlayerData(sender))
        })
    }

    override fun permission(): Permission = Permission.ofString(Namespaces.Utopianism, "player.menu")
}