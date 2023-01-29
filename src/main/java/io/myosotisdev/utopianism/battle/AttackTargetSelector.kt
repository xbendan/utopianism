package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.modules.player.PlayerEx
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.entity.LivingEntity

abstract class AttackTargetSelector
{
    abstract fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>

    abstract fun applyEffects(entity: Entity, vararg args: Any?)
}