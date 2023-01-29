package io.myosotisdev.utopianism.modules

import com.google.gson.JsonParser
import io.myosotisdev.minestom.Expire
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.chat.PlayerDisplayName
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ChatManager : ModuleManager(Name)
{
    companion object
    {
        val Name: String = "chat"
    }

    val displayNameRecords: HashMap<UUID, PlayerDisplayName> = HashMap()
    val nametags: MutableList<Expire<String>> = ArrayList()

    override fun onEnable()
    {
        val file = File(dataFolder, "nametags.json")
        if (file.exists())
        {
            val json = JsonParser.parseReader(FileReader(file)).asJsonObject
            json.entrySet()
                    .forEach { entry ->
                        nametags.add(Expire(entry.key, entry.value.asLong))
                    }
        }
    }

    override fun onDisable()
    {

    }

    fun getCustomName(uuid: UUID): String = loadPlayer(uuid)?.customName ?: "?"

    fun getDisplayName(uuid: UUID): String = loadPlayer(uuid)?.displayName ?: "?"

    fun getNametags(uuid: UUID): Collection<String>
    {
        val name = loadPlayer(uuid)
        name?.checkNametags()
        return name?.nametags?.keys ?: Collections.emptyList()
    }

    fun loadPlayer(uuid: UUID): PlayerDisplayName?
    {
        val name = displayNameRecords[uuid]
        if (name != null)
            return name

        val file = File(dataFolder, "names/$uuid.json")
        return if (file.exists()) PlayerDisplayName(JsonParser.parseReader(FileReader(file)).asJsonObject) else null
    }
}