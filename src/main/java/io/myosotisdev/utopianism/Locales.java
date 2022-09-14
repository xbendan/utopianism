package io.myosotisdev.utopianism;

import net.minestom.server.utils.NamespaceID;

import java.util.HashMap;
import java.util.Locale;

public class Locales
{
    public static Locale DefaultLocaleSetting = Locale.ENGLISH;
    private static final String EMPTY = "NO-MATCHED-STRING";
    private static HashMap<Locale, HashMap<NamespaceID, String>> Locales = new HashMap<>();

    public static String get(Locale locale, NamespaceID namespace)
    {
        HashMap<NamespaceID, String> localeStrings = Locales.get(locale);
        if(localeStrings != null)
            return localeStrings.getOrDefault(namespace, EMPTY);
        else
            return Locales.get(DefaultLocaleSetting).getOrDefault(namespace, EMPTY);
    }

    public static String get(Locale locale, String namespace)
    {
        return get(locale, NamespaceID.from("utopianism", namespace));
    }
}
