package io.myosotisdev.utopianism.modules.stat.type

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.data.StringData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class StringStat(key: String) : ItemStat<String>(key, Tag.String("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in String>)
    {
        return
    }

    override fun defaultStatData(): ItemStatData<String>?
    {
        return StringData()
    }

    override fun getLoadedData(itemStack: ItemStack): String
    {
        return "null"
    }
}