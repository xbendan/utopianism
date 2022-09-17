package io.myosotisdev.utopianism.modules.stat.type

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.IntegerData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class IntegerStat(key: String) : ItemStat<Int>(key, Tag.Integer("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Int>)
    {
        return
    }

    override fun defaultStatData(): ItemStatData<Int>?
    {
        return IntegerData()
    }

    override fun getLoadedData(itemStack: ItemStack): Int
    {
        return 0
    }
}