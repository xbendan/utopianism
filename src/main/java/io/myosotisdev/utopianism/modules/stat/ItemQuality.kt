package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.item.Quality
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.data.QualityData
import io.myosotisdev.utopianism.serialize.QualityTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack

class ItemQuality : ItemStat<Quality>("quality", Tags.createTag("quality", Serializer))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Quality>)
    {

    }

    override fun defaultStatData(): ItemStatData<Quality> = QualityData()
    override fun createStatData(jsonObject: JsonObject): ItemStatData<Quality> = QualityData(value = Quality.valueOf(jsonObject.asString))

    companion object
    {
        val Serializer: Serializer<Quality> = QualityTagSerializer()
    }
}