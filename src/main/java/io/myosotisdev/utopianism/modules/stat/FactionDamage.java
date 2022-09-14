package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.util.Tags;
import net.minestom.server.item.ItemStack;

public class FactionDamage extends ItemStat
{
    public FactionDamage()
    {
        super("faction-damage", Tags.createTag("faction-damage", null));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return null;
    }
}
