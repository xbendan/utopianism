package io.myosotisdev.utopianism.modules.team

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.item.ItemStack

object Teams
{
    fun makeItemStack(team: Team): ItemStack = ItemStacks.newHead(
            team.leader.uuid,
            "&e${Server.getCustomName(team.leader.uuid)} 的队伍",
            team.players.map { "&7${Server.getDisplayName(it.uuid)}" }
    )
}