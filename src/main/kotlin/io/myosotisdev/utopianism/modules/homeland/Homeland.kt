package io.myosotisdev.utopianism.modules.homeland

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.modules.player.PlayerEx
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.WorldBorder
import net.minestom.server.inventory.Inventory
import net.minestom.server.item.Material
import java.util.*

class Homeland : IJsonData
{
    var instance: Instance? = null
    val members: MutableList<UUID> = ArrayList()
    var level: Int = 0
    val uuid: UUID
    var lock: Boolean = false

    val farmStorages: HashMap<Material, Inventory> = HashMap()

    var allowStrangersVisit: Boolean = false
    var allowFriendsVisit: Boolean = false

    val border: WorldBorder?
        get() = instance?.worldBorder

    constructor(player: Player)
    {
        this.uuid = player.uuid
        this.members.add(player.uuid)
    }

    constructor(uuid: UUID, jsonObject: JsonObject?)
    {
        this.uuid = uuid

        if(jsonObject != null)
        {

        }
    }

    fun rankup()
    {

    }

    fun disband()
    {

    }

    fun loadWorld()
    {

    }

    fun unloadWorld()
    {

    }

    override fun asJsonCopy(): JsonElement
    {
        TODO("Not yet implemented")
    }
}