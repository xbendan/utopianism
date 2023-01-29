package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.clan.*
import io.myosotisdev.utopianism.modules.player.clan
import io.myosotisdev.utopianism.util.ItemStacks
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class VisualClan(val player: Player) : VisualInventory(player.clan!!.name)
{
    val clan: Clan = player.clan!!

    init
    {
        dividers(SlotAround, DividerBlackPanel)
        slots(4, 20, 21, 22, 23, 24, 29, 30, 31, 33)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots)
        {
            setItemStack(slot, when (slot)
            {
                4    -> ClanVars.makeIcon(clan)
                20   ->
                {
                    ItemStacks.new(Material.CHEST,
                            "&a公会成员 &7[&e${clan.getOnlineMembers().size}&7/${clan.members.size} 在线&7]",
                            clan.getOnlineMembers().map {
                                if (it.player == player) "${Server.getCustomName(it.playerUuid)} &7（你）"
                                else Server.getCustomName(it.playerUuid)
                            }
                                    .plus("&a点击&7查看成员列表"))
                }
                21   -> ItemStacks.new(Material.EMERALD,
                        "&a公会银行",
                        listOf("&7当前金额： &e${clan.bank.money}G", "", "&a左键&7存款", "&c右键&7取款"))
                22   ->
                {
                    val itemStack = clan.tier.itemStack
                    itemStack.withLore(listOf(*itemStack.lore.toTypedArray(),
                            Component.text()
                                    .build(),
                            Components.fromLegacy("&aShift+左键&7升级公会等级")))
                }
                23   -> ItemStacks.new(Material.PLAYER_HEAD,
                        "&a招新策略",
                        ClanJoinPolicy.values()
                                .map { policy -> if (clan.joinPolicy == policy) "&6&l>> &f${policy.text} &6&l<<" else "   &7${policy.text}" }
                                .plus(" ")
                                .plus("&a左键&7轮换到下一个策略")
                                .plus("&c右键&7编辑策略属性"))
                24   -> ItemStacks.new(Material.DIAMOND_SWORD,
                        "&a游玩策略",
                        ClanPlayPolicy.values()
                                .map { policy -> if (clan.playPolicy == policy) "&6&l>> &f${policy.text} &6&l<<" else "   &7${policy.text}" }
                                .plus(" ")
                                .plus("&a左键&7轮换到下一个策略"),
                        0,
                        ItemHideFlag.HIDE_ATTRIBUTES)
                29   -> ItemStacks.new(Material.BREWING_STAND, "&a公会科研")
                30   -> ItemStacks.new(Material.STONE_BRICK_WALL, "&a公会领地")
                31   -> ItemStacks.new(Material.SHIELD, "&a公会战争")
                33   -> ItemStacks.new(Material.COMMAND_BLOCK, "&c公会管理")
                else -> ItemStack.AIR
            })
        }
        update()
    }
}
