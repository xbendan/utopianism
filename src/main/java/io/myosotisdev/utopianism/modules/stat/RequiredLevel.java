package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.type.IntegerStat;
import net.minestom.server.item.ItemStack;

public class RequiredLevel extends IntegerStat
{
    public RequiredLevel()
    {
        super("required-level");
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        super.onApply(itemStack, data);
    }
}
