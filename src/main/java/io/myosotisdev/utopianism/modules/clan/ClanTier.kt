package io.myosotisdev.utopianism.modules.clan

import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

enum class ClanTier(val level: Int, val title: String, val cost: Int, maxVault: Int,
                     val maxBalance: Double, val membersToRankup: Int, maxAllies: Int)
{
    BRONZE(0, "&2铜牌", 250000, 3, 1000000.0, 1, 3),
    SLIVER(1, "&f白银", 400000, 6, 1800000.0, 3, 8),
    GOLD(2, "&6黄金", 1000000,  9, 4000000.0, 8, 20),
    PLATINUM(3, "&b白金", 3000000,  12, 15000000.0, 15, 35),
    DIAMOND(4, "&b&l钻石", 6000000,  18, 35000000.0, 40, 60),
    LEGEND(5, "", 15000000, 24, 80000000.0, 100, 150);

    val maxVaultAmount: Int = maxVault
    val maxAllyAmount: Int = maxAllies

    val itemStack: ItemStack
        get() = ItemStacks.new(
                Material.NAME_TAG,
                "&a公会等级： $title",
                listOf(
                        "&7仓库数量上限: &e${maxVaultAmount}",
                        "&7银行储值上限: &e${maxBalance}",
                        "&7最大盟友数量: &e${maxAllyAmount}",
                        "&7升级所需人数: &e${if(membersToRankup > 0) membersToRankup else "已达最高等级"}",
                        "&7升级所需金额: &e${if(cost > 0) cost else "已达最高等级"}"
                ),
                level + 1
        )
}