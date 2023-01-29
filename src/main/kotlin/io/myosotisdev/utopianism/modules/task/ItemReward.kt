package io.myosotisdev.utopianism.modules.task

import io.myosotisdev.utopianism.modules.item.ItemStackBuilder
import io.myosotisdev.utopianism.modules.item.ItemStackTemplate
import io.myosotisdev.utopianism.modules.player.PlayerEx
import net.minestom.server.item.ItemStack

class ItemReward(val item: ItemStackTemplate, val amount: Int) : Reward(RewardType.ITEM)
{
    override fun applyReward(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }

    fun createReward(): ItemStack
    {
        return ItemStackBuilder(item).createItemStacks(amount)
    }
}