package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.utopianism.modules.clan.ClanVars
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class VisualClanCreate : VisualInventory("创建/加入公会", InventoryType.CHEST_3_ROW)
{
    init
    {
        slots(11, 15)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots)
        {
            setItemStack(slot, when(slot)
            {
                11 -> ItemStacks.new(Material.GOLD_INGOT, "&a创建公会", listOf("&f创建一个属于自己的公会", "&f你将成为公会会长", "&f需要花费: &e${ClanVars.CreationCost}G", "", "&a左键&7点击以输入公会名"))
                15 -> ItemStacks.new(Material.CHEST_MINECART, "&a加入公会", listOf("&f从已有的公会选择一个并加入", "&f你将成为正式成员", "", "&a左键&7以打开公会列表", "&c右键&7以直接输入公会名"))
                else -> ItemStack.AIR
            })
        }
    }
}