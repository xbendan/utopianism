package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.faction.FactionDamage
import io.myosotisdev.utopianism.modules.stat.data.FactionDamageData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.util.Tags.createTag
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class FactionDamage : ItemStat<FactionDamage>("faction-damage", createTag("faction-damage", FactionDamageData.Serializer) as Tag<FactionDamage>)
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in FactionDamage>)
    {

    }

    override fun defaultStatData(): ItemStatData<FactionDamage>?
    {
        return null
    }
}