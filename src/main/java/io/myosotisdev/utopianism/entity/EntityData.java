package io.myosotisdev.utopianism.entity;

import net.minestom.server.entity.Entity;

public abstract class EntityData
{
    private Entity targetingEntity;

    public EntityData(Entity entity)
    {
        this.targetingEntity = entity;
    }

    public Entity getEntity()
    {
        return this.targetingEntity;
    }
}
