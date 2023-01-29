package io.myosotisdev.utopianism.modules.task

import io.myosotisdev.utopianism.modules.player.PlayerEx

class MoneyReward(val amount: Double) : Reward(RewardType.MONEY)
{
    override fun applyReward(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }
}