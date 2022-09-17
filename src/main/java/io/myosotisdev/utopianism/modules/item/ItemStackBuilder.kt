package io.myosotisdev.utopianism.modules.item

import io.myosotisdev.utopianism.modules.stat.ItemStat
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemMeta
import net.minestom.server.item.ItemStack

class ItemStackBuilder(private val template: ItemTemplate)
{
    fun createItemStacks(amount: Int): ItemStack
    {
        val builderItemStack = ItemStack.builder(template.material!!)
        val newStack = builderItemStack.amount(amount)
                .displayName(Component.text(template.name!!))
                .lore(template.loreTemplate
                        ?.createLores(template) ?: ArrayList())
                .build()
                .withMeta { builder: ItemMeta.Builder ->
                    builder.customModelData(template.customModelId)
                            .unbreakable(template.isUnbreakable)
                            .damage(template.durability)
                            .build()
                }
        // Need Update
        template.itemStats
                .forEach { (itemStat: ItemStat<*>, itemStatData: ItemStatData<*>): Map.Entry<ItemStat<*>, ItemStatData<*>> ->
                    itemStat.onApply(newStack, itemStatData as ItemStatData<Any?>)
                }
        return newStack
    }
}