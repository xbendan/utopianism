package io.myosotisdev.utopianism.modules.player;

import io.myosotisdev.minestom.entity.PlayerEx;
import io.myosotisdev.utopianism.battle.attributes.Health;
import io.myosotisdev.utopianism.entity.PlayerData;
import io.myosotisdev.utopianism.modules.Economy;
import io.myosotisdev.utopianism.modules.Experience;
import io.myosotisdev.utopianism.modules.expert.SkillExpert;
import io.myosotisdev.utopianism.modules.guild.Guild;
import io.myosotisdev.utopianism.modules.guild.GuildClass;
import io.myosotisdev.utopianism.modules.guild.IGuildMember;
import io.myosotisdev.utopianism.modules.task.ITaskExecutor;
import io.myosotisdev.utopianism.modules.team.ITeamMember;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ImplPlayer
        extends PlayerEx
        implements ITeamMember, ITaskExecutor, IGuildMember
{
    private Guild guild;
    private GuildClass guildClass;
    private PlayerData data;
    private Economy economy;
    private String locale;

    public ImplPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection)
    {
        super(uuid, username, playerConnection);
    }

    @Override
    public void setGuildClass(GuildClass gClass)
    {

    }

    @Override
    public GuildClass getGuildClass()
    {
        return this.guildClass;
    }

    public Health getHealthBar()
    {
        return data.getHealth();
    }

    @Override
    public float getHealth()
    {
        return super.getHealth();
    }

    public Guild getGuild()
    {
        return guild;
    }

    public PlayerData getData()
    {
        return data;
    }

    public Experience getExpertExperience(SkillExpert skillExpert)
    {
        return data.getExperts()
                   .get(skillExpert);
    }

    public int getExpertLevel(SkillExpert expert)
    {
        return data.getExpertLevel(expert);
    }

    public Economy getEconomy()
    {
        return economy;
    }

    public double getMoney()
    {
        return economy.getMoney();
    }
}
