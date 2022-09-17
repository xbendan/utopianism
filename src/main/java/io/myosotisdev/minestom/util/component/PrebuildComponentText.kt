package io.myosotisdev.minestom.util.component

import io.myosotisdev.utopianism.modules.guild.Guild
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minestom.server.entity.Player

class PrebuildComponentText(private val originText: String)
{
    private var current: String

    init
    {
        current = originText
    }

    fun guild_name(guild: Guild): PrebuildComponentText
    {
        return replaceText("%<guild_name>", guild.name)
    }

    fun guild_members(guild: Guild): PrebuildComponentText
    {
        return replaceText("%<guild_members>", guild.players.size.toString())
    }

    fun player_name(player: Player): PrebuildComponentText
    {
        return player_name(player.name.examinableName())
    }

    fun player_name(name: String?): PrebuildComponentText
    {
        return replaceText("%<player>", name)
    }

    fun replaceText(from: String, to: String?): PrebuildComponentText
    {
        current = current.replace(from.toRegex(), to!!)
        return this
    }

    fun build(): Component
    {
        return if (originText.startsWith("json:")) GsonComponentSerializer.gson()
                .deserialize(current.replace("json:", ""))
        else LegacyComponentSerializer.legacyAmpersand()
                .deserialize(current)
    }
}