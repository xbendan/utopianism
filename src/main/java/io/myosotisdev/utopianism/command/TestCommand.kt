package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Effects
import net.minestom.server.entity.Player

class TestCommand : AbstractCommand(null, "test")
{
    init
    {
        addSyntax({ sender, _ ->
            //Effects.playTotemOfUndying(sender as Player, 0)
            if (sender is Player)
            {
                val itemStack = sender.itemInMainHand
                if (!itemStack.isAir)
                {
                    sender.itemInMainHand = itemStack.withAmount(80)
                }
            }
        })
    }

    override fun permission(): Permission?
    {
        return null
    }
}