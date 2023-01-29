package io.myosotisdev.minestom.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object Components
{
    fun fromLegacy(str: String): Component
    {
        return LegacyComponentSerializer.legacyAmpersand()
                .deserialize(str).decoration(TextDecoration.ITALIC, false)
    }

    fun asLegacyCopy(comp: Component): String = LegacyComponentSerializer.legacyAmpersand().serialize(comp)

    fun fromLegacy(vararg str: String): Array<Component> = str.asList().map { str -> fromLegacy(str) }.toTypedArray()
}