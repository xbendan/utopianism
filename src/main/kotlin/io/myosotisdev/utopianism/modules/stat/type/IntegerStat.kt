package io.myosotisdev.utopianism.modules.stat.type

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.IntegerData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class IntegerStat(key: String) : ItemStat<Int>(key, Tag.Integer("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Int>)
    {

    }

    override fun defaultStatData(): ItemStatData<Int> = IntegerData()

    override fun createStatData(jsonObject: JsonObject): ItemStatData<Int> = IntegerData(jsonObject.asInt)

    override fun getLoadedData(itemStack: ItemStack): Int
    {
        return 0
    }
}