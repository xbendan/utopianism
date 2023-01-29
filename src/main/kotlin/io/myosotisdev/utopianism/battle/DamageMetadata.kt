package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.entitydata.Entities
import io.myosotisdev.utopianism.modules.entitydata.EntityData
import io.myosotisdev.utopianism.modules.entitydata.LivingEntityData
import io.myosotisdev.utopianism.util.IntegerTemplate
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.item.ItemStack

class DamageMetadata
{
    val projectile: EntityProjectile?
    var attacker: EntityData?
    val victim: LivingEntityData?
    val weapon: ItemStack?
    val damageModifiers: Map<String, DamageModifier> = HashMap()
    val defenseModifiers: Map<String, DefenseModifier> = HashMap()
    val damageMap: Map<DamageModifier.DamageType, IntegerTemplate>? = null
    val isProjectileDamage
        get() = attacker?.entity is EntityProjectile
    var isSpellDamage = false
    var isTrapDamage = false


    constructor(event: EntityAttackEvent)
    {
        attacker = Server.getEntityData(event.entity)
        victim = Server.getEntityData(event.target) as LivingEntityData

        if (isProjectileDamage)
        {
            this.projectile = event.entity as EntityProjectile
            if (this.projectile.shooter != null)
            {
                this.attacker = Server.getEntityData(projectile.shooter!!) as LivingEntityData
            }
        }
        else
            this.projectile = null

        if(attacker is LivingEntityData)
        {
            weapon = (attacker as LivingEntityData).livingEntity.itemInMainHand
        }
        else
            weapon = null
    }
}