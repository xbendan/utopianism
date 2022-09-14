package io.myosotisdev.utopianism.listeners;

import io.myosotisdev.utopianism.battle.DamageMeta;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.event.*;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.entity.EntityDeathEvent;
import net.minestom.server.event.trait.EntityEvent;

import java.util.function.Consumer;

public class EntityEvents
{
    private EventNode<EntityEvent> node;
    private EventNode<EntityEvent> node_LivingEntity;
    Consumer<EntityAttackEvent> consumer_EntityAttackEvent;
    Consumer<EntityDeathEvent> consumer_EntityDeathEvent;

    public EntityEvents()
    {
        MinecraftServer.getGlobalEventHandler()
                       .addChild(node = EventNode.type("entity-events", EventFilter.ENTITY));

        node_LivingEntity = EventNode.event("living-entity-events", EventFilter.ENTITY, entityEvent -> entityEvent.getEntity() instanceof LivingEntity);

        consumer_EntityAttackEvent = event -> {
            LivingEntity victim = (LivingEntity) event.getEntity();

            DamageMeta damageMeta = new DamageMeta((LivingEntity) event.getEntity(), (LivingEntity) event.getTarget());
        };
        node.addListener(EntityAttackEvent.class, consumer_EntityAttackEvent);

        consumer_EntityDeathEvent = event -> {

        };
    }
}
