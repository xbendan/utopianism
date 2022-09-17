package io.myosotisdev.utopianism.entity

import io.myosotisdev.utopianism.battle.BattleClass
import io.myosotisdev.utopianism.modules.Experience
import io.myosotisdev.utopianism.modules.expert.SkillExpert
import io.myosotisdev.utopianism.modules.player.MsPlayer
import io.myosotisdev.utopianism.util.SingleSelectionModel

class PlayerData(player: MsPlayer?) : LivingEntityData(player!!)
{
    val battleClassModel: SingleSelectionModel<String, BattleClass>? = SingleSelectionModel()
    private val expertExperiences: HashMap<SkillExpert, Experience> = HashMap()
    val player: MsPlayer
        get() = entity as MsPlayer
    val experts: Map<SkillExpert, Experience>
        get() = expertExperiences

    fun getExpertLevel(expert: SkillExpert): Int
    {
        return expertExperiences[expert]!!
                .level
    }
}