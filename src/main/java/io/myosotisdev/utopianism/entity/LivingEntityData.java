package io.myosotisdev.utopianism.entity;

import io.myosotisdev.utopianism.battle.attributes.Health;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.LivingEntity;

public abstract class LivingEntityData extends EntityData
{
    private Health health;

    public LivingEntityData(LivingEntity entity)
    {
        super(entity);
    }

    public Health getHealth()
    {
        return this.health;
    }

    public void damage(double val)
    {

    }

    public void heal(double val)
    {

    }

    public void kill()
    {

    }

    public LivingEntity getLivingEntity()
    {
        return ((LivingEntity) getEntity());
    }
}
