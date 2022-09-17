package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.type.BooleanStat
import net.minestom.server.item.ItemStack

class Unbreakable : BooleanStat("unbreakable")
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Boolean>)
    {
        super.onApply(itemStack, data)
    }
}