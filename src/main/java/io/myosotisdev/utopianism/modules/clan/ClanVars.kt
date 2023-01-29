package io.myosotisdev.utopianism.modules.clan

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.PlayerHeadMeta

object ClanVars
{
    var CreationCost: Double = 150000.0

    fun makeIcon(clan: Clan): ItemStack = ItemStack.builder(Material.PLAYER_HEAD)
            .meta(PlayerHeadMeta::class.java) { metadata ->
                metadata.displayName(Components.fromLegacy(clan.name))
                        .lore(*Components.fromLegacy(
                                "&7公会等级: ${clan.tier.title}",
                                "&7公会成员: &e${clan.members.size}&f/${clan.maxMembers}",
                                "&7会长: &a${Server.getDisplayName(clan.clanMaster.playerUuid)}",
                                "&7申请策略: &a${clan.joinPolicy.text}",
                                "&7游玩方针: &a${clan.playPolicy.text}",
                                "&c本月活跃度: &e${clan.monthlyActivity}"
                        ))
                        .build()
                if (clan.displayTextures != null) metadata.skullOwner(clan.displayTextures)
            }
            .build()
}