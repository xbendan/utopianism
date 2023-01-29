package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class VisualConfirmation(title: String) : VisualInventory("确认操作/${title}", InventoryType.CHEST_3_ROW)
{
    init
    {
        slots(11, 15)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots)
        {
            setItemStack(slot, when (slot)
            {
                11   -> ItemStacks.new(Material.LIME_WOOL, "&a确认", listOf("&7我已理解本次操作的危险性", "&7并要继续执行"))
                15   -> ItemStacks.new(Material.RED_WOOL, "&c取消", listOf("&7我点错了/反悔了"))
                else -> ItemStack.AIR
            })
        }
    }
}