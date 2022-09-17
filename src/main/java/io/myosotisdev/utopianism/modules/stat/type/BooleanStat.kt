package io.myosotisdev.utopianism.modules.stat.type

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.BooleanData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class BooleanStat(key: String) : ItemStat<Boolean>(key, Tag.Boolean("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Boolean>)
    {
        TODO("Not yet implemented")
    }

    override fun defaultStatData(): ItemStatData<Boolean>?
    {
        return BooleanData(false)
    }

    override fun getLoadedData(itemStack: ItemStack): Boolean
    {
        return false
    }
}