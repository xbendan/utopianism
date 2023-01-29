package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.data.BooleanData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class Unbreakable : ItemStat<Boolean>("unbreakable", Tag.Boolean("Unbreakable"))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Boolean>)
    {
        itemStack.withTag(tag, data.value)
    }

    override fun defaultStatData(): ItemStatData<Boolean> = BooleanData(false)

    override fun createStatData(jsonObject: JsonObject): ItemStatData<Boolean> = BooleanData(jsonObject.asBoolean)
}