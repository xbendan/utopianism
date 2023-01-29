package io.myosotisdev.utopianism.modules.item

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.minestom.server.item.Enchantment
import net.minestom.server.item.Material
import java.util.Objects

class ItemStackTemplate(val key: String, jsonObject: JsonObject)
{
    var name: String? = null
    var material: Material? = null
    var loreTemplate: LoreTemplate? = null
    var durability = 0
    var customModelData = 0
    var isUnbreakable = false
    val enchants: HashMap<Enchantment, Int> = HashMap()
    val itemStats: HashMap<ItemStat<*>, ItemStatData<*>> = HashMap()

    init
    {
        this.name = jsonObject["name"].asString
        this.material = Material.fromNamespaceId("minecraft:${jsonObject["material"].asString}")
        this.durability = jsonObject["durability"].asInt
        this.customModelData = jsonObject["customModelData"].asInt
        this.isUnbreakable = jsonObject["unbreakable"].asBoolean
        jsonObject["enchants"]?.asJsonObject?.entrySet()
                ?.forEach {
                    val enchantment = Enchantment.fromNamespaceId("minecraft:${it.key.lowercase()}")
                    if (Objects.nonNull(enchantment)) this.enchants[enchantment!!] = it.value.asInt
                }

        for (statEntry in jsonObject["stats"].asJsonObject.entrySet())
        {
            val stat = ItemStat.getByName(statEntry.key) ?: continue
            itemStats[stat] = stat.createStatData(statEntry.value.asJsonObject)
        }
    }

    fun setLores(loreTemplate: LoreTemplate)
    {

    }
}