package io.myosotisdev.utopianism.listeners

import io.myosotisdev.utopianism.battle.DamageMetadata
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.LivingEntity
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.event.entity.projectile.ProjectileCollideWithBlockEvent
import net.minestom.server.event.entity.projectile.ProjectileCollideWithEntityEvent
import net.minestom.server.event.trait.EntityEvent
import java.util.function.Consumer

class EntityEvents
{
    private var node: EventNode<EntityEvent>
    val consumer_EntityAttackEvent: Consumer<EntityAttackEvent> = Consumer { event: EntityAttackEvent ->
        val victim = event.entity as LivingEntity
        val damageMetadata = DamageMetadata(event)
    }
    val consumer_EntityDeathEvent: Consumer<EntityDeathEvent> = Consumer {

    }
    val consumer_ProjectileHitEntityEvent: Consumer<ProjectileCollideWithEntityEvent> = Consumer { event ->

    }
    val consumer_ProjectileHitGroundEvent: Consumer<ProjectileCollideWithBlockEvent> = Consumer { event ->

    }

    init
    {
        MinecraftServer.getGlobalEventHandler()
                .addChild(EventNode.type("entity-events", EventFilter.ENTITY)
                        .also { node = it })

        node.addListener(EntityAttackEvent::class.java, consumer_EntityAttackEvent)
        node.addListener(EntityDeathEvent::class.java, consumer_EntityDeathEvent)
        node.addListener(ProjectileCollideWithEntityEvent::class.java, consumer_ProjectileHitEntityEvent)
        node.addListener(ProjectileCollideWithBlockEvent::class.java, consumer_ProjectileHitGroundEvent)

    }
}