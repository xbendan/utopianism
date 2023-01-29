package io.myosotisdev.utopianism.modules

import com.google.gson.JsonObject
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.entitydata.EntityData
import io.myosotisdev.utopianism.modules.entitydata.Entities
import io.myosotisdev.utopianism.modules.entitydata.LivingEntityData
import io.myosotisdev.utopianism.modules.entitydata.PlayerData
import io.myosotisdev.utopianism.modules.player.data
import io.myosotisdev.utopianism.util.JsonObjects
import net.minestom.server.entity.*
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import java.io.File
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap

class EntityDataManager : ModuleManager(Name)
{
    companion object
    {
        val Name: String = "entity-data"
    }

    private val entityDatas: HashMap<EntityType, HashMap<UUID, EntityData>> = HashMap()

    override fun onEnable()
    {
        EntityType.values()
                .stream()
                .forEach { entityDatas[it] = HashMap() }
        addListener(AsyncPlayerPreLoginEvent::class.java, Consumer {
            set (it.player, it.player.data)
        })
        addListener(PlayerDisconnectEvent::class.java, Consumer {
            val profile = File("profiles/${it.player.uuid}.json")
            val data = get(it.player) as PlayerData
            JsonObjects.writeFile(data.asJsonCopy(), profile)
            entityDatas[EntityType.PLAYER]!!.remove(it.player.uuid)
        })
    }

    override fun onDisable()
    {
    }

    fun createLivingEntityData(entity: LivingEntity): LivingEntityData
    {
        return LivingEntityData(entity).also { entityDatas[entity.entityType]!![entity.uuid] = it }
    }

    fun createEntityData(entity: Entity): EntityData
    {
        var data: EntityData = LivingEntityData(entity as LivingEntity)
        when
        {

        }
        this.entityDatas[entity.entityType]!!.put(entity.uuid, data)
        return data
    }

    fun addEntityData(entity: Entity, data: EntityData)
    {
        this.entityDatas[entity.entityType]!!.put(entity.uuid, data)
    }

    operator fun get(entity: Entity): EntityData?
    {
        return entityDatas[entity.entityType]!![entity.uuid]
    }

    fun get(type: EntityType, uuid: UUID): EntityData?
    {
        return entityDatas[type]!![uuid]
    }

    fun getPlayerData(uuid: UUID): PlayerData?
    {
        return get(EntityType.PLAYER, uuid) as PlayerData?
    }

    operator fun set(entity: Entity, data: EntityData)
    {
        entityDatas[entity.entityType]!![entity.uuid] = data
    }

    fun loadPlayerData(player: Player): PlayerData?
    {
        var data: PlayerData? = getPlayerData(player.uuid)
        if (data == null)
        {
            val jsonObject: JsonObject? = JsonObjects.readFile("profiles/${player.uuid}.json")?.asJsonObject
            if (jsonObject != null)
            {
                data = PlayerData(player, jsonObject)
            }
        }
        return data
    }
}