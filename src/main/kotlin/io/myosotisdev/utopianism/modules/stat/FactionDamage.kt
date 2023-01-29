package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.faction.FactionDamage
import io.myosotisdev.utopianism.modules.stat.data.FactionDamageData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack

class FactionDamage : ItemStat<FactionDamage>("faction-damage", Tags.createTag("faction-damage", FactionDamageData.Serializer))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out FactionDamage>)
    {

    }

    override fun defaultStatData(): ItemStatData<FactionDamage>?
    {
        return null
    }

    override fun createStatData(jsonObject: JsonObject): ItemStatData<FactionDamage>
    {
        TODO("Not yet implemented")
    }
}