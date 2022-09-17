package io.myosotisdev.utopianism.entity

import io.myosotisdev.utopianism.battle.attributes.Health
import net.minestom.server.entity.LivingEntity

interface ILivingEntity : IBattleEntity
{
    val target: LivingEntity
    val healthBar: Health?
}