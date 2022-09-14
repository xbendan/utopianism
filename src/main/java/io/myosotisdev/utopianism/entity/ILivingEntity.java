package io.myosotisdev.utopianism.entity;

import io.myosotisdev.utopianism.battle.attributes.Health;
import net.minestom.server.entity.LivingEntity;

public interface ILivingEntity extends IBattleEntity
{
    LivingEntity getTarget();

    Health getHealthBar();
}
