package io.myosotisdev.utopianism.modules.task

import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.modules.item.Items
import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.modules.player.SkillExpert

abstract class Reward(val type: RewardType)
{
    abstract fun applyReward(player: PlayerEx);

    companion object
    {
        fun deserialize(string: String): Reward?
        {
            var keys: List<String> = string.split(",")
            return when (keys[0])
            {
                "money" -> MoneyReward(keys[1].toDouble())
                "item" -> ItemReward(Items.getByName(keys[1])!!, keys[2].toInt())
                "class-exp" -> ClassExpReward()
                "expert-exp" -> ExpertExpReward(SkillExpert.valueOf(keys[1].uppercase()), keys[2].toDouble())
                "perm" -> PermissionReward(Permission.Companion.ofString(keys[1])!!)
                "advancement" -> AdvancementReward()
                else -> null
            }
        }
    }
}