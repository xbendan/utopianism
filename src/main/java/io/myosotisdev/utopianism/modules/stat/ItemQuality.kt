package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.item.Quality
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.serialize.QualityTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class ItemQuality : ItemStat<Quality>("quality", Tags.createTag("quality", Serializer) as Tag<Quality>)
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in Quality>)
    {
        TODO("Not yet implemented")
    }

    override fun defaultStatData(): ItemStatData<Quality>?
    {
        return null
    }

    companion object
    {
        val Serializer: Serializer<Quality> = QualityTagSerializer()
    }
}