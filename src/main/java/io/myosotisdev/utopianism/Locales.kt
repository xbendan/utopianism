package io.myosotisdev.utopianism

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.module.LocaleModule
import net.minestom.server.entity.Player
import net.minestom.server.utils.NamespaceID
import java.util.*

object Locales
{
    private val localeModule: LocaleModule? = Minestom.getModule("locale") as LocaleModule?
    fun translate(locale: Locale?, namespace: NamespaceID): String
    {
        return localeModule!!.translate(locale, namespace)
    }

    fun translate(locale: Locale?, string: String): String
    {
        return translate(locale, NamespaceID.from(string!!))
    }

    fun translate(player: Player?, namespace: NamespaceID): String
    {
        return translate(player?.locale ?: null, namespace)
    }

    fun translate(player: Player?, string: String): String
    {
        return translate(player?.locale ?: null, NamespaceID.from(string!!))
    }

    fun translate(string: String?): String
    {
        return translate(localeModule!!.defaultLocale, NamespaceID.from(string!!))
    }
}