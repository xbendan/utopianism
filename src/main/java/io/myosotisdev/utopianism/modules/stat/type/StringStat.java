package io.myosotisdev.utopianism.modules.stat.type;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.data.StringData;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public class StringStat extends ItemStat<String>
{
    public StringStat(String key)
    {
        super(key, Tag.String("item-" + key));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new StringData();
    }

    @Override
    public String getLoadedData(ItemStack itemStack)
    {
        return null;
    }
}
