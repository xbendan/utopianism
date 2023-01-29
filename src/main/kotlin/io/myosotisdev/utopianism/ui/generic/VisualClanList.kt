package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.clan.Clan
import io.myosotisdev.utopianism.modules.clan.ClanVars
import net.minestom.server.item.ItemStack

class VisualClanList() : VisualInventory("公会列表")
{
    var page: Int = 0
    val clans: Collection<Clan> = Server.clans().all()

    init
    {
        dividers(SlotListView)
        loadPage(page)
    }

    fun loadPage(page: Int)
    {
        this.page = page

        var slotIndex: Int = 0
        val sublist = clans.stream()
                .skip((page * 36).toLong())
                .iterator()

        while (slotIndex < 36 && sublist.hasNext())
        {
            val g = sublist.next()
            setItemStack(slotIndex, ClanVars.makeIcon(g))

            slotIndex++
        }

        slots(45, 53)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots)
        {
            setItemStack(slot, when(slot)
            {
                45 -> if (page > 0) Prev else ItemStack.AIR
                53 -> if (clans.size > page * 36) Next else ItemStack.AIR
                else -> ItemStack.AIR
            })
        }
    }
}