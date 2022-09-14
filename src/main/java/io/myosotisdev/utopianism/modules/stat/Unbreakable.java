package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.type.BooleanStat;
import net.minestom.server.item.ItemStack;

public class Unbreakable extends BooleanStat
{
    public Unbreakable()
    {
        super("unbreakable");
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        super.onApply(itemStack, data);
    }
}
