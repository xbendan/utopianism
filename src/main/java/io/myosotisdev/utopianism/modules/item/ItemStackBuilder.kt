package io.myosotisdev.utopianism.modules.item

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemMeta
import net.minestom.server.item.ItemStack

class ItemStackBuilder(private val template: ItemStackTemplate)
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
                    builder.customModelData(template.customModelData)
                            .unbreakable(template.isUnbreakable)
                            .damage(template.durability)
                            .build()
                }
        // Need Update
        template.itemStats.entries.forEach {
            it.key.onApply(newStack, it.value as ItemStatData<Nothing>)
        }
        return newStack
    }
}