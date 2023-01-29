package io.myosotisdev.utopianism.modules

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.item.ItemStackTemplate
import java.io.FileReader

class ItemManager : ModuleManager("item")
{
    val itemTemplates: HashMap<String, ItemStackTemplate> = HashMap()

    override fun onEnable()
    {
        dataFolder.listFiles()
                ?.forEach {
                    if (it.name.endsWith(".json")) try
                    {
                        val fileJson = JsonParser.parseReader(FileReader(it))
                        fileJson.asJsonObject.entrySet()
                                .forEach { itemTemplates[it.key] = ItemStackTemplate(it.key, it.value.asJsonObject) }
                    }
                    catch (ex: java.lang.Exception)
                    {
                        ex.printStackTrace()
                    }
                }
    }

    override fun onDisable()
    {
    }

    fun parse(key: String, jsonObject: JsonObject)
    {

    }

    fun getByName(name: String): ItemStackTemplate?
    {
        return itemTemplates[name]
    }
}