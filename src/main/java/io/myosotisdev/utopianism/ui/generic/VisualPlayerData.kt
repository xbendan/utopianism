package io.myosotisdev.utopianism.ui.generic

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.Exp
import io.myosotisdev.utopianism.modules.battlepass.BattlePass
import io.myosotisdev.utopianism.modules.entitydata.Entities
import io.myosotisdev.utopianism.modules.clan.ClanVars
import io.myosotisdev.utopianism.modules.homeland.Homelands
import io.myosotisdev.utopianism.modules.team.Teams
import io.myosotisdev.utopianism.modules.player.clan
import io.myosotisdev.utopianism.modules.player.homeland
import io.myosotisdev.utopianism.modules.player.money
import io.myosotisdev.utopianism.modules.team.Team
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.entity.Player
import net.minestom.server.item.Material

class VisualPlayerData(val player: Player) : VisualInventory("玩家信息")
{
    init
    {
        dividers(SlotLeftDivide, DividerBlackPanel)
        slots(10, 12, 13, 14, 16, 19, 21, 22, 23, 24, 28, 31, 32, 33, 37, 41)
    }

    override fun slots(vararg slots: Int)
    {
        for (slot in slots) when (slot)
        {
            10 -> setItemStack(10, Entities.makeItemStack(player))
            12 -> setItemStack(12, if (player.clan != null) ClanVars.makeIcon(player.clan!!)
            else ItemStacks.new(Material.FURNACE, "&f无公会", listOf(" ", "&a点击&7创建公会/浏览公会列表")))
            13 -> setItemStack(13, if (player.getTag(Team.TeamTag) != null) Teams.makeItemStack(player.getTag(Team.TeamTag)!!)
            else ItemStacks.new(Material.HOPPER_MINECART, "&f无队伍", listOf(" ", "&a左键&7创建队伍", "&c右键&7输入队伍邀请码")))
            14 -> setItemStack(14, if (player.homeland != null) Homelands.makeItemStack(player.homeland!!)
            else ItemStacks.new(Material.OAK_DOOR, "&f无家园世界", listOf(" ", "&a左键&7创建一个家园世界")))
            16 -> setItemStack(16, ItemStacks.new(Material.OAK_SIGN, "&a邮箱", listOf(" ", "&a点击&7打开邮箱")))
            19 -> setItemStack(19,
                    ItemStacks.new(Material.EMERALD, "&a金钱", listOf("&7持有金币：&e${player.money}G", "&7加成倍率：&a1.0x")))
            21 -> setItemStack(21, ItemStacks.new(Material.WRITABLE_BOOK, "&a任务"))
            22 -> setItemStack(22, ItemStacks.new(Material.LECTERN, "&a图鉴"))
            23 -> setItemStack(23, ItemStacks.new(Material.FILLED_MAP, "&a世界地图"))
            24 -> setItemStack(24, ItemStacks.new(Material.ENDER_PEARL, "&a传送锚点"))
            28 ->
            {
                val bpData = BattlePass.loadData(player.uuid)
                setItemStack(28, if (bpData != null) ItemStacks.new(Material.PAPER, "&a冒险者勋章", listOf(""))
                else ItemStacks.new(Material.PAPER,
                        "&a冒险者勋章&7/未激活",
                        listOf("&7购买冒险者勋章可在冒险进程中",
                                "&7获取更多的&e&l金币&7、&b&l宝石&7、&6&l体力",
                                "&7以及&5&l稀有物品",
                                " ",
                                "&a左键&7可输入购买的激活码")))
            }
            31 -> setItemStack(31, ItemStacks.new(Material.IRON_PICKAXE, "&a专家树", Server.getPlayerData(player).experts.map { entry ->
                String.format("&b%s：&6%d★ &7[%s&7]", entry.key.displayText, entry.value.level, Exp.progressBar(entry.value))
            }
                    .plus(" ")
                    .plus("&a点击&7打开专家面板")))
            32 -> setItemStack(32, ItemStacks.new(Material.BREWING_STAND, "&a研究"))
            33 -> setItemStack(33, ItemStacks.new(Material.DIAMOND_SWORD, "&a职业"))
            37 ->
            {
                val names = Server.getChatManager().loadPlayer(player.uuid)!!
                setItemStack(37,
                        ItemStacks.new(Material.NAME_TAG,
                                "&a称号",
                                listOf("&7持有 &e${names.nametags.size} &7个称号",
                                        "&7当前前缀：&f${names.prefix ?: "无"}",
                                        "&7当前后缀: &f${names.suffix ?: "无"}",
                                        " ",
                                        "&a左键&7更换前缀",
                                        "&c右键&7更换后缀",
                                        "&b中键&7卸下当前装备的称号")))
            }
            41 -> setItemStack(41, ItemStacks.new(Material.EXPERIENCE_BOTTLE, "&a经验"))
        }
    }
}