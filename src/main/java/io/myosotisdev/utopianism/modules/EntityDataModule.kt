package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.Module
import io.myosotisdev.utopianism.Ut
import io.myosotisdev.utopianism.entity.EntityData
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType

class EntityDataModule : Module(Ut.ModuleEntityData)
{
    private val entityDatas: HashMap<EntityType, HashMap<Entity, EntityData>>? = HashMap()

    init
    {
        EntityType.values()
                .stream()
                .forEach { entityType: EntityType -> entityDatas!![entityType] = HashMap() }
    }

    override fun onEnable()
    {
    }

    override fun onDisable()
    {
    }

    fun getData(entity: Entity): EntityData?
    {
        return entityDatas!![entity.entityType]!![entity]
    }
}