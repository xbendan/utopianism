package io.myosotisdev.minestom.module

import com.google.common.collect.Maps
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.minestom.server.entity.Player
import net.minestom.server.utils.NamespaceID
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*

class LocaleModule : ModuleManager("locale")
{
    var defaultLocale: Locale = Locale.getDefault()
    private val Empty = "NO-MATCHED-STRING"
    private val localeTranslationMaps: HashMap<Locale?, MutableMap<NamespaceID, String>> = HashMap()

    fun translate(locale: Locale?, namespace: NamespaceID): String
    {
        var returnedValue: String = ""
        var componentTransMap = localeTranslationMaps[locale]
        if (componentTransMap != null && componentTransMap[namespace].also { returnedValue = it!! } != null)
        {
            return returnedValue
        }
        componentTransMap = localeTranslationMaps[defaultLocale]
        return componentTransMap?.getOrDefault(namespace, "Invalid key [$namespace]")
               ?: Empty
    }

    fun translate(player: Player, namespace: NamespaceID): String
    {
        return translate(player.locale, namespace)
    }

    fun translate(player: Player, string: String?): String
    {
        return translate(player.locale, NamespaceID.from(string!!))
    }

    fun translate(string: String?): String
    {
        return translate(defaultLocale, NamespaceID.from(string!!))
    }

    fun parse(locale: Locale?, domain: String?, path: String, jsonObject: JsonObject)
    {
        var locale = locale
        if (locale == null) locale = defaultLocale
        var components: MutableMap<NamespaceID, String>?
        if (localeTranslationMaps[locale].also { components = it } == null)
        {
            localeTranslationMaps[locale] = Maps.newHashMap<NamespaceID, String>()
                    .also { components = it }
        }
        for ((key, value) in jsonObject.entrySet())
        {
            if (value
                            .isJsonObject)
            {
                val newObject = value
                        .asJsonObject
                val newPath = "$path.$key"
                parse(locale, domain, newPath, newObject)
            }
            else components?.putIfAbsent(NamespaceID.from(domain!!, path), value
                    .asString)
        }
    }

    override fun onEnable()
    {
        val supportedLocales = Arrays.asList(*Locale.getAvailableLocales())
        val localeFiles = dataFolder.listFiles { dir: File?, name: String ->
            name.endsWith(".json") &&
            supportedLocales.stream()
                    .anyMatch { locale: Locale ->
                        locale.getDisplayName(Locale.ENGLISH)
                                .equals(name.replace(".json", ""), ignoreCase = true)
                    }
        }

        try
        {
            for (localeFile in localeFiles) parse(Locale.forLanguageTag(localeFile.name
                    .replace(".json", "")),
                    null,
                    "",
                    JsonParser.parseReader(FileReader(localeFile))
                            .asJsonObject)
            val configObject = JsonParser.parseReader(FileReader(saveConfig()))
                    .asJsonObject
            if (configObject.has("locale"))
            {
                val localeName = configObject["locale"]
                        .asString
                val locale = Locale.forLanguageTag(localeName)
                if (locale != null) defaultLocale = locale
            }
        }
        catch (e: IOException)
        {
            throw RuntimeException(e)
        }
    }

    override fun onDisable()
    {
    }

    companion object
    {
        const val JsonText = "json:"
    }
}