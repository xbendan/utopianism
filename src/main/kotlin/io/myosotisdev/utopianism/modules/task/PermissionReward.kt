package io.myosotisdev.utopianism.modules.task

import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.modules.player.PlayerEx

class PermissionReward(val perm: Permission) : Reward(RewardType.PERMISSION)
{
    override fun applyReward(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }
}