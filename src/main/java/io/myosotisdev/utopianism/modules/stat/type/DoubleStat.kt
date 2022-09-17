package io.myosotisdev.utopianism.modules.stat.type

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class DoubleStat(key: String) : ItemStat<Double>(key, Tag.Double("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Double>)
    {
        itemStack.withTag(tag, data.value as Double?)
    }

    override fun defaultStatData(): ItemStatData<Double>?
    {
        return null
    }

    override fun getLoadedData(itemStack: ItemStack): Double
    {
        return 0.0
    }
}