package io.myosotisdev.utopianism.modules.stat.type;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import io.myosotisdev.utopianism.modules.stat.data.IntegerData;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public class IntegerStat extends ItemStat<Integer>
{
    public IntegerStat(String key)
    {
        super(key, Tag.Integer("item-" + key));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new IntegerData();
    }

    @Override
    public Integer getLoadedData(ItemStack itemStack)
    {
        return null;
    }
}
