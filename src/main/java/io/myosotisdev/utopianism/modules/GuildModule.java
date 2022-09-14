package io.myosotisdev.utopianism.modules;

import io.myosotisdev.minestom.module.Module;
import io.myosotisdev.utopianism.modules.guild.Guild;
import io.myosotisdev.utopianism.util.Namespaces;

import java.util.HashMap;
import java.util.UUID;

public class GuildModule extends Module
{
    private HashMap<String, Guild> guilds;

    public GuildModule()
    {
        super(Namespaces.Utopianism + "-" + "guild");

        this.guilds = new HashMap<>();
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }
}
