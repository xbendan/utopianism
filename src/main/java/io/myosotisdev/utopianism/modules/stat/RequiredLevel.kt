package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.type.IntegerStat
import net.minestom.server.item.ItemStack

class RequiredLevel : IntegerStat("required-level")
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Int>)
    {
        super.onApply(itemStack, data)
    }
}