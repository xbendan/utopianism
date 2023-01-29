package io.myosotisdev.utopianism.modules.stat.type

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.BooleanData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

open class BooleanStat(key: String) : ItemStat<Boolean>(key, Tag.Boolean("item-$key"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Boolean>)
    {

    }

    override fun defaultStatData(): ItemStatData<Boolean> = BooleanData(false)

    override fun createStatData(jsonObject: JsonObject): ItemStatData<Boolean> = BooleanData(jsonObject.asBoolean)

    override fun getLoadedData(itemStack: ItemStack): Boolean
    {
        return false
    }
}