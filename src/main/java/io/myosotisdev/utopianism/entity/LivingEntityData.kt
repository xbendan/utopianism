package io.myosotisdev.utopianism.entity

import io.myosotisdev.utopianism.battle.attributes.Health
import net.minestom.server.entity.LivingEntity

abstract class LivingEntityData(entity: LivingEntity) : EntityData(entity)
{
    val health: Health = Health()

    fun damage(value: Double)
    {
    }

    fun heal(value: Double)
    {
    }

    fun kill()
    {
    }

    val livingEntity: LivingEntity
        get() = entity as LivingEntity
}