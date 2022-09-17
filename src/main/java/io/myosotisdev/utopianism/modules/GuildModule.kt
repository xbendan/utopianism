package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.Module
import io.myosotisdev.utopianism.Ut
import io.myosotisdev.utopianism.modules.guild.Guild

class GuildModule : Module(Ut.ModuleGuild)
{
    private val guilds: HashMap<String, Guild> = HashMap()

    override fun onEnable()
    {
    }

    override fun onDisable()
    {
    }
}