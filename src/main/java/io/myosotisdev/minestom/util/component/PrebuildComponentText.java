package io.myosotisdev.minestom.util.component;

import io.myosotisdev.utopianism.modules.guild.Guild;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minestom.server.entity.Player;

public class PrebuildComponentText
{
    private String originText;
    private String current;

    public PrebuildComponentText(String fromText)
    {
        this.originText = fromText;
        this.current = fromText;
    }

    public PrebuildComponentText guild_name(Guild guild)
    {
        return replaceText("%<guild_name>", guild.getName());
    }

    public PrebuildComponentText guild_members(Guild guild)
    {
        return replaceText("%<guild_members>", String.valueOf(guild.getPlayers().size()));
    }

    public PrebuildComponentText player_name(Player player)
    {
        return player_name(player.getName().examinableName());
    }

    public PrebuildComponentText player_name(String name)
    {
        return replaceText("%<player>", name);
    }

    public PrebuildComponentText replaceText(String from, String to)
    {
        this.current = current.replaceAll(from, to);
        return this;
    }

    public Component build()
    {
        return originText.startsWith("json:") ?
                GsonComponentSerializer.gson()
                                       .deserialize(current.replace("json:", "")) :
                LegacyComponentSerializer.legacyAmpersand()
                                         .deserialize(current);
    }
}
