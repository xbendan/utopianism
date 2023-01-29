package io.myosotisdev.utopianism.modules.stat.type

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.DoubleData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class DoubleStat(key: String) : ItemStat<Double>(key, Tag.Double("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Double>)
    {
        itemStack.withTag(tag, data.value)
    }

    override fun defaultStatData(): ItemStatData<Double>?
    {
        return null
    }

    override fun createStatData(jsonObject: JsonObject): ItemStatData<Double> = DoubleData(jsonObject.asDouble)

    override fun getLoadedData(itemStack: ItemStack): Double
    {
        return 0.0
    }
}