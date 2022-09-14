package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.type.DoubleStat;
import net.minestom.server.item.ItemStack;

public class AttackDamage extends DoubleStat
{
    public AttackDamage()
    {
        super("attack-damage");
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        super.onApply(itemStack, data);
    }
}
