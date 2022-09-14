package io.myosotisdev.utopianism.modules.stat.type;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import io.myosotisdev.utopianism.modules.stat.data.BooleanData;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public class BooleanStat extends ItemStat<Boolean>
{
    public BooleanStat(String key)
    {
        super(key, Tag.Boolean("item-" + key));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new BooleanData(false);
    }

    @Override
    public Boolean getLoadedData(ItemStack itemStack)
    {
        return null;
    }
}
