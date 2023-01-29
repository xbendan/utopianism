package io.myosotisdev.utopianism.modules

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.modules.advancement.Advancements
import net.minestom.server.MinecraftServer
import net.minestom.server.advancements.AdvancementManager
import net.minestom.server.advancements.AdvancementRoot
import net.minestom.server.advancements.FrameType
import net.minestom.server.item.Material
import java.io.File
import java.io.FileReader
import java.util.function.Consumer

class AdvancementModule : ModuleManager(Name)
{
    companion object
    {
        val Name: String = "advancement"
    }

    val manager: AdvancementManager
        get() = MinecraftServer.getAdvancementManager()

    override fun onEnable()
    {
        Advancements.Instance = this

        var configFile: File = File(dataFolder,"config.json")
        if(configFile.exists())
        {

            var tabArray: MutableIterator<JsonElement> = JsonParser.parseReader(FileReader(configFile)).asJsonArray.iterator()
            while(tabArray.hasNext())
            {

            }
        }
    }

    override fun onDisable()
    {
    }

    fun addTab(name: String)
    {
    }

    fun addTab(json: JsonObject)
    {
        var tabLoader: Consumer<JsonObject> = Consumer { treeNode ->

        }
        manager.createTab(json["tab-name"].asString, AdvancementRoot(
                Components.fromLegacy(json["title"].asString),
                Components.fromLegacy(json["description"].asString),
                Material.fromNamespaceId(json["material"].asString) ?: Material.GRASS_BLOCK,
                FrameType.valueOf(json["frame"].asString.uppercase()),
                0.0f,
                0.0f,
                null
        ))
    }

    fun addAdvancement()
    {

    }
}