package io.myosotisdev.utopianism.modules.stat.type;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public class DoubleStat extends ItemStat<Double>
{
    public DoubleStat(String key)
    {
        super(key, Tag.Double("item-" + key));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData<Double> data)
    {
        itemStack.withTag(getTag(), data.getData());
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return null;
    }

    @Override
    public Double getLoadedData(ItemStack itemStack)
    {
        return null;
    }
}
