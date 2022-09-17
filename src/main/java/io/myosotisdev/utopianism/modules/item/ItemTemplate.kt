package io.myosotisdev.utopianism.modules.item

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.Enchantment
import net.minestom.server.item.Material

class ItemTemplate
{
    var name: String? = null
    var material: Material? = null
    var loreTemplate: LoreTemplate? = null
    var durability = 0
    var customModelId = 0
    var isUnbreakable = false
    val enchants: HashMap<Enchantment, Int>? = null
    val itemStats: HashMap<ItemStat<*>, ItemStatData<*>> = HashMap()
}