package io.myosotisdev.utopianism.listeners

import io.myosotisdev.utopianism.battle.DamageMeta
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.LivingEntity
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.event.trait.EntityEvent
import java.util.function.Consumer

class EntityEvents
{
    private var node: EventNode<EntityEvent>? = null
    private val node_LivingEntity: EventNode<EntityEvent>
    var consumer_EntityAttackEvent: Consumer<EntityAttackEvent>
    var consumer_EntityDeathEvent: Consumer<EntityDeathEvent>

    init
    {
        MinecraftServer.getGlobalEventHandler()
                .addChild(EventNode.type("entity-events", EventFilter.ENTITY)
                        .also { node = it })
        node_LivingEntity = EventNode.event("living-entity-events", EventFilter.ENTITY) { entityEvent: EntityEvent -> entityEvent.entity is LivingEntity }
        consumer_EntityAttackEvent = Consumer { event: EntityAttackEvent ->
            val victim = event.entity as LivingEntity
            val damageMeta = DamageMeta(event.entity as LivingEntity, event.target as LivingEntity)
        }
        node!!.addListener(EntityAttackEvent::class.java, consumer_EntityAttackEvent)
        consumer_EntityDeathEvent = Consumer { event: EntityDeathEvent? -> }
    }
}