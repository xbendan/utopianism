package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.clan.Clan
import io.myosotisdev.utopianism.modules.player.clan
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import java.util.*

class VisualClanMembers(player: Player, val clan: Clan = player.clan!!) : VisualInventory("${clan.name}&r的公会成员")
{
    var page: Int = 0

    init
    {
        loadPage(0)
    }

    fun loadPage(page: Int)
    {
        this.page = page

        var slotIndex = 0
        val sublist = clan.members.entries.stream()
                .skip((page * 45).toLong())
                .iterator()

        while (slotIndex < 45 && sublist.hasNext())
        {
            val entry = sublist.next()
            val member = entry.value
            if (Objects.isNull(member)) continue
            setItemStack(slotIndex,
                    ItemStacks.newHead(entry.key,
                            "&a${Server.getDisplayName(entry.key)}",
                            listOf(member.level.text,
                                    "&7于 &e${member.joinTime} &7加入公会",
                                    "&7向公会贡献了 &e${member.contribution}Pt")))

            slotIndex++
        }

        slots(45, 53)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots)
        {
            setItemStack(slot, when (slot)
            {
                45   -> if (page > 0) Prev else ItemStack.AIR
                53   -> if (clan.members.size > (page + 1) * 45) Next else ItemStack.AIR
                else -> ItemStack.AIR
            })
        }
    }
}