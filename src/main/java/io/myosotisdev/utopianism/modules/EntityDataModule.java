package io.myosotisdev.utopianism.modules;

import io.myosotisdev.minestom.module.Module;
import io.myosotisdev.utopianism.entity.EntityData;
import io.myosotisdev.utopianism.util.Namespaces;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;

import java.util.HashMap;

public class EntityDataModule extends Module
{
    private HashMap<EntityType, HashMap<Entity, EntityData>> entityDatas;

    public EntityDataModule()
    {
        super(Namespaces.Utopianism + "-" + "entity_data");
        EntityType.values()
                  .stream()
                  .forEach(entityType -> entityDatas.put(entityType, new HashMap<>()));
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }

    public EntityData getData(Entity entity)
    {
        return this.entityDatas.get(entity.getEntityType()).get(entity);
    }
}
