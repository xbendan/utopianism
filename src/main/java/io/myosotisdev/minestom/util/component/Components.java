package io.myosotisdev.minestom.util.component;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Components
{
    public static Component fromLegacy(String str)
    {
        return LegacyComponentSerializer.legacyAmpersand()
                                        .deserialize(str);
    }
}
