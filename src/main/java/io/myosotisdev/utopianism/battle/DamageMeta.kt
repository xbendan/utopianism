package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.Ut
import io.myosotisdev.utopianism.entity.LivingEntityData
import io.myosotisdev.utopianism.util.IntegerTemplate
import net.minestom.server.entity.LivingEntity
import net.minestom.server.item.ItemStack

class DamageMeta internal constructor()
{
    val attacker: LivingEntityData? = null
    val victim: LivingEntityData? = null
    val weapon: ItemStack? = null
    val damageModifiers: Map<String, DamageModifier>
    val defenseModifiers: Map<String, DefenseModifier>
    val damageMap: Map<DamageModifier.DamageType, IntegerTemplate>? = null
    var isProjectileDamage = false
    var isSpellDamage = false
    var isTrapDamage = false

    init
    {
        damageModifiers = HashMap()
        defenseModifiers = HashMap()
    }

    constructor(attacker: LivingEntityData?, victim: LivingEntityData?) : this()
    {
    }

    constructor(attacker: LivingEntity?, victim: LivingEntity?) : this(if (attacker != null) Ut.entitydata()
            .getData(attacker) as LivingEntityData
    else null,
            if (victim != null) Ut.entitydata()
                    .getData(victim) as LivingEntityData
            else null)
    {
    }
}