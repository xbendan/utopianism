package io.myosotisdev.utopianism.entity;

import io.myosotisdev.utopianism.battle.BattleClass;
import io.myosotisdev.utopianism.modules.Experience;
import io.myosotisdev.utopianism.modules.expert.SkillExpert;
import io.myosotisdev.utopianism.modules.player.ImplPlayer;
import io.myosotisdev.utopianism.util.SingleSelectionModel;

import java.util.HashMap;
import java.util.Map;

public class PlayerData extends LivingEntityData
{
    private SingleSelectionModel<String, BattleClass> battleClassModel;
    private HashMap<SkillExpert, Experience> expertExperiences;

    public PlayerData(ImplPlayer player)
    {
        super(player);
    }

    public ImplPlayer getPlayer()
    {
        return ((ImplPlayer) getEntity());
    }

    public SingleSelectionModel<String, BattleClass> getBattleClassModel()
    {
        return this.battleClassModel;
    }

    public Map<SkillExpert, Experience> getExperts()
    {
        return this.expertExperiences;
    }

    public int getExpertLevel(SkillExpert expert)
    {
        return this.expertExperiences.get(expert).getLevel();
    }
}
