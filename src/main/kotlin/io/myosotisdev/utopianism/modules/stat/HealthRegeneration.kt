package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.type.DoubleStat
import net.minestom.server.item.ItemStack

class HealthRegeneration : DoubleStat("health-regen")
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out Double>)
    {

    }
}