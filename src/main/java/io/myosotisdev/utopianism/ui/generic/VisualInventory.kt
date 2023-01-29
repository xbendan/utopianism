package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.util.ItemStacks
import net.kyori.adventure.text.Component
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag

abstract class VisualInventory(title: String, type: InventoryType = InventoryType.CHEST_6_ROW, lastLevel: VisualInventory? = null) : Inventory(type, Components.fromLegacy(title))
{
    companion object
    {
        val TagLocked: Tag<Boolean> = Tag.Boolean("inventory-locked")
        val DividerFill: ItemStack = ItemStack.builder(Material.DIAMOND_HOE)
                .meta { meta ->
                    meta.hideFlag(
                            ItemHideFlag.HIDE_ATTRIBUTES
                    )
                            .customModelData(1)
                            .displayName(Component.text(" "))
                            .build()
                }
                .build()
        val DividerBlackPanel: ItemStack = ItemStacks.new(Material.BLACK_STAINED_GLASS_PANE, " ")
        val SlotAround: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17,
                18, 26,
                27, 35,
                36, 44,
                45, 46, 47, 48, 49, 50, 51, 52, 53)
        val SlotListView: Array<Int> = arrayOf(
                36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        val SlotLeftDivide: Array<Int> = arrayOf(
                2, 11, 20, 29, 38, 47
        )
        val Prev: ItemStack = ItemStack.builder(Material.PAPER)
                .meta { meta ->
                    meta.customModelData(1)
                            .displayName(Components.fromLegacy("&a上一页"))
                            .build()
                }
                .build()
        val Next: ItemStack = ItemStack.builder(Material.PAPER)
                .meta { metadata ->
                    metadata.customModelData(2)
                            .displayName(Components.fromLegacy("&a下一页"))
                            .build()
                }
                .build()
    }

    init
    {
        this.setTag(TagLocked, true)
    }

    fun dividers(slots: Array<Int>, divider: ItemStack = DividerFill)
    {
        slots.forEach { slotId -> setItemStack(slotId, divider) }
    }

    abstract fun slots(vararg slots: Int);
}