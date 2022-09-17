package io.myosotisdev.utopianism.util

import io.myosotisdev.utopianism.modules.stat.ItemStat
import net.minestom.server.item.ItemStack

object Items
{
    fun compare(stack0: ItemStack, stack1: ItemStack): Boolean
    {
        return (Tags.compare(ItemStat.ITEM_ID.tag, stack0, stack1)
                && Tags.compare(ItemStat.REINFORCE.tag, stack0, stack1)
                && Tags.compare(ItemStat.UNBREAKABLE.tag, stack0, stack1))
    }
}