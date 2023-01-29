package io.myosotisdev.utopianism.modules.task

import io.myosotisdev.utopianism.modules.player.SkillExpert
import io.myosotisdev.utopianism.modules.player.PlayerEx

class ExpertExpReward(val expert: SkillExpert, val amount: Double) : Reward(RewardType.EXPERT_EXP)
{
    override fun applyReward(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }
}