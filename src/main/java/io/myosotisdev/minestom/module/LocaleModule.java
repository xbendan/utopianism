package io.myosotisdev.minestom.module;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.myosotisdev.minestom.util.component.PrebuildComponentText;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.NamespaceID;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class LocaleModule
        extends Module
{
    public static final String JsonText = "json:";
    private Locale                                       defaultLocale;
    private HashMap<Locale, Map<NamespaceID, String>> localeTranslationMaps;

    public LocaleModule()
    {
        super("locale");

        this.defaultLocale = Locale.getDefault();
        this.localeTranslationMaps = Maps.newHashMap();
    }

    public String translate(Locale locale, NamespaceID namespace)
    {
        String returnedValue;
        Map<NamespaceID, String> componentTransMap = localeTranslationMaps.get(locale);
        if (componentTransMap != null && (returnedValue = componentTransMap.get(namespace)) != null)
        {
            return returnedValue;
        }

        componentTransMap = localeTranslationMaps.get(defaultLocale);
        if (componentTransMap == null)
            throw new NoSuchElementException("The default locale has no such translation map.");
        else
        {
            return componentTransMap.getOrDefault(namespace, "Invalid key [" + namespace.toString() + "]");
        }
    }

    public String translate(Player player, NamespaceID namespace)
    {
        return translate(player.getLocale(), namespace);
    }

    public String translate(Player player, String string)
    {
        return translate(player.getLocale(), NamespaceID.from(string));
    }

    public String translate(String string)
    {
        return translate(defaultLocale, NamespaceID.from(string));
    }

    public void parse(Locale locale, String domain, String path, JsonObject jsonObject)
    {
        if(locale == null)
            locale = defaultLocale;

        Map<NamespaceID, String> components;
        if((components = localeTranslationMaps.get(locale)) == null)
        {
            localeTranslationMaps.put(locale, components = Maps.newHashMap());
        }

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet())
        {
            if(entry.getValue().isJsonObject())
            {
                JsonObject newObject = entry.getValue()
                                            .getAsJsonObject();
                if(Strings.isNullOrEmpty(domain))
                    parse(locale, entry.getKey(), path, newObject);
                else
                {
                    String newPath = path + "." + entry.getKey();
                    parse(locale, domain, newPath, newObject);
                }
            }
            else
                components.putIfAbsent(NamespaceID.from(domain, path), entry.getValue().getAsString());
        }
    }

    @Override
    public void onEnable()
    {
        getLogger().log(Level.INFO, GsonComponentSerializer.gson()
                                                           .serialize(Component.text("Test message", TextColor.color(30, 144, 255))));

        List<Locale> supportedLocales = Arrays.asList(Locale.getAvailableLocales());
        File[] localeFiles = getDataFolder().listFiles((dir, name) -> name.endsWith(".json") &&
                supportedLocales.stream()
                                .anyMatch(locale -> locale.getDisplayName(Locale.ENGLISH)
                                                          .equalsIgnoreCase(name.replace(".json", ""))));

        try
        {
            for (File localeFile : localeFiles)
                parse(Locale.forLanguageTag(localeFile.getName()
                                                      .replace(".json", "")),
                        null,
                        "",
                        JsonParser.parseReader(new FileReader(localeFile))
                                  .getAsJsonObject());

            JsonObject configObject = JsonParser.parseReader(new FileReader(saveConfig()))
                                                .getAsJsonObject();
            if (configObject.has("locale"))
            {
                String localeName = configObject.get("locale")
                                                .getAsString();
                Locale locale = Locale.forLanguageTag(localeName);
                if (locale != null)
                    this.defaultLocale = locale;
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable()
    {

    }
}
