package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.type.DoubleStat
import net.minestom.server.item.ItemStack

class AttackSpeed : DoubleStat("attack-speed")
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Double>)
    {
        super.onApply(itemStack, data)
    }
}