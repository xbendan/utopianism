package io.myosotisdev.utopianism.modules.chat

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.minestom.util.Components
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import java.util.*
import kotlin.collections.HashMap

class PlayerDisplayName : IJsonData
{
    val playerUuid: UUID
    val nametags: HashMap<String, Long> = HashMap()

    var name: String
    var customName: String
    var prefix: String?
    var suffix: String?

    val displayName: String
        get() = "&r${prefix ?: ""}$customName${(suffix ?: "")}".trim()

    constructor(player: Player)
    {
        playerUuid = player.uuid
        name = Components.asLegacyCopy(player.name)
        customName = name
        prefix = null
        suffix = null
    }

    constructor(jsonObject: JsonObject)
    {
        playerUuid = UUID.fromString(jsonObject["uuid"].asString)
        jsonObject["nametags"].asJsonObject.entrySet()
                .forEach {
                    nametags[it.key] = it.value.asLong
                }
        name = jsonObject["name"].asString
        customName = jsonObject["customName"].asString
        prefix = jsonObject["prefix"].asString
        suffix = jsonObject["suffix"].asString
    }

    fun isNametagValid(nametag: String): Boolean = (nametags[nametag] ?: 0) > System.currentTimeMillis()

    fun checkNametags()
    {
        val currentTime = System.currentTimeMillis()

        nametags.entries.forEach {
            if (it.value <= currentTime) nametags.remove(it.key)
        }

        if (Objects.nonNull(prefix) && !nametags.containsKey(prefix)) prefix = null
        if (Objects.nonNull(suffix) && !nametags.containsKey(suffix)) suffix = null
    }

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()
        val nametagsJson = JsonObject()

        nametags.entries.forEach { nametagsJson.addProperty(it.key, it.value) }
        jsonObject.addProperty("uuid", playerUuid.toString())
        jsonObject.add("nametags", nametagsJson)
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("customName", customName)
        jsonObject.addProperty("prefix", prefix)
        jsonObject.addProperty("suffix", suffix)

        return jsonObject
    }
}