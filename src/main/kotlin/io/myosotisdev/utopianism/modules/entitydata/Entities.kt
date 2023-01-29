package io.myosotisdev.utopianism.modules.entitydata

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.manager.BattleManager
import io.myosotisdev.utopianism.modules.player.clan
import io.myosotisdev.utopianism.modules.player.homeland
import io.myosotisdev.utopianism.modules.team.Team
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

object Entities
{
    fun makeItemStack(player: Player): ItemStack
    {
        val data = Server.getPlayerData(player)
        return ItemStacks.newHead(player.uuid,
                Server.getDisplayName(player),
                listOf(
                        player.getTag(BattleManager.StateTag).text,
                        "&7公会：&a${player.clan?.name ?: "&f无公会"}",
                        "&7组队：&a${player.getTag(Team.TeamTag)?.leader?.name ?: "&f无组队"}",
                        "&7家园：&b${player.homeland?.level ?: 0}☆",
                        "&7职业：&6${data.classModel.currentKey?.name ?: "&f无职业"}${data.getBattleLevel()}★",
                        "&7成就：&aLv.0"
                ))
    }
}