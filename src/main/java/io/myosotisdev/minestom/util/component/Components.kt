package io.myosotisdev.minestom.util.component

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object Components
{
    fun fromLegacy(str: String?): Component
    {
        return LegacyComponentSerializer.legacyAmpersand()
                .deserialize(str!!)
    }

    fun builder(str: String): PrebuildComponentText
    {
        return PrebuildComponentText(str);
    }
}