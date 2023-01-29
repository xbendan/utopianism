package io.myosotisdev.utopianism.battle

import io.myosotisdev.minestom.extension.InstanceExtension.spawnParticle
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.particle.Particle
import java.util.*
import java.util.function.Predicate

enum class AttackTargetType(val selector: AttackTargetSelector?)
{
    ONE(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            return if (entity is Player)
            {
                listOf(entity.getLineOfSightEntity(args[0] as Double, { it is LivingEntity }) as LivingEntity)
            }
            else
            {
                listOf()
            }
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
        }
    }),
    LINE(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            val entities: ArrayList<LivingEntity> = ArrayList()
            val range: Double = args[0] as Double

            when (entity)
            {
                is Player           ->
                {
                    val eyeSight: Pos = entity.position.add(0.0, entity.eyeHeight, 0.0)
                    val iterator: MutableIterator<Entity>? = entity.instance?.getNearbyEntities(eyeSight, range)
                            ?.iterator()

                    while (iterator?.hasNext() == true)
                    {
                        var entity: Entity = iterator.next()
                        if (entity is LivingEntity &&
                            !Objects.equals(entity, entity) &&
                            entity.position.distanceSquared(eyeSight) < (range * range) &&
                            // problem here
                            eyeSight.asVec()
                                    .angle(entity.position.asVec()
                                            .sub(entity.position.asVec())) < 0.2617993877991494)
                        {
                            entities.add(entity)
                        }
                    }
                }
                is EntityProjectile ->
                {

                }
                else                -> null
            }

            return entities
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
            val eyeSight = entity.position.add(0.0, entity.eyeHeight, 0.0)
            val canSee: ArrayList<Player> = ArrayList()

            /**
             * Need extra code here
             * Deal with Player and Projectile respectively
             */
            var var5: Double = ((eyeSight.yaw + 90.0) / 180.0) * Math.PI
            var var7: Double = (-eyeSight.pitch / 180.0) * Math.PI

            var var9 = 1.0
            while (var9 < 5.0)
            {
                var var11 = -0.2617993877991494
                while (var11 < 0.2617993877991494)
                {
                    entity.instance?.spawnParticle(
                            Particle.CRIT,
                            eyeSight.add(Math.cos(var11 + var5) * var9, Math.sin(var7) * var9, Math.sin(var11 + var5) * var9),
                            canSee = canSee
                    )
                    var11 += 0.19634954084936207 / var9
                }
                var9 += 0.3
            }
        }
    }),
    SECTOR(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            TODO("Not yet implemented")
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
            TODO("Not yet implemented")
        }
    }),
    SPHERE(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            val entities: ArrayList<LivingEntity> = ArrayList()
            val range: Double = args[0] as Double
            val radius: Double = args[1] as Double

            when (entity)
            {
                is Player           ->
                {
                    val victimEntity: LivingEntity? = entity.getLineOfSightEntity(args[0] as Double) { entity -> entity is LivingEntity } as LivingEntity?
                    if (victimEntity != null)
                    {
                        val collection: MutableCollection<Entity>? = entity.instance?.getNearbyEntities(entity.position, range)
                        collection?.forEach { nearbyEntity ->
                            if (entity is LivingEntity &&
                                !Objects.equals(entity, entity) &&
                                nearbyEntity.position.distanceSquared(entity.position) < (radius * radius))
                            {
                                entities.add(entity)
                            }
                        }
                    }
                }
                is EntityProjectile ->
                {

                }
                else                -> null
            }

            return entities
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
            TODO("Not yet implemented")
        }
    }),
    ANNULUS(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            TODO("Not yet implemented")
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
            TODO("Not yet implemented")
        }
    }),
    RECTANGLE(object : AttackTargetSelector()
    {
        override fun getTargets(entity: Entity, vararg args: Any?): Collection<LivingEntity>
        {
            TODO("Not yet implemented")
        }

        override fun applyEffects(entity: Entity, vararg args: Any?)
        {
            TODO("Not yet implemented")
        }
    }),
    NONE(null)
    ;
}