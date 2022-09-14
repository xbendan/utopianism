package io.myosotisdev.utopianism.util;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import net.minestom.server.item.ItemStack;

public class Items
{
    public static boolean compare(ItemStack stack0, ItemStack stack1)
    {
        return Tags.compare(ItemStat.ITEM_ID.getTag(), stack0, stack1)
                && Tags.compare(ItemStat.REINFORCE.getTag(), stack0, stack1)
                && Tags.compare(ItemStat.UNBREAKABLE.getTag(), stack0, stack1);
    }
}
