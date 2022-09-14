package io.myosotisdev.utopianism.modules.team;

import io.myosotisdev.utopianism.modules.player.ImplPlayer;
import io.myosotisdev.utopianism.modules.task.ITaskExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Team
        extends net.minestom.server.scoreboard.Team
        implements ITaskExecutor
{
    private ImplPlayer leader;

    protected Team(@NotNull String teamName)
    {
        super(teamName);
    }

    public void join(ImplPlayer player)
    {

    }

    public void kick(ImplPlayer player)
    {

    }

    public void invite(ImplPlayer player)
    {

    }

    public void setLeader(ImplPlayer paramOfflinePlayer)
    {

    }

    public ImplPlayer getLeader()
    {
        return this.leader;
    }

    public List<ImplPlayer> getOnlines()
    {
        List<ImplPlayer> onlineList = new ArrayList<>();
        getPlayers().stream()
                    .forEach(player -> {
                        if (player.isOnline())
                            onlineList.add((ImplPlayer) player);
                    });
        return onlineList;
    }

    public void convene(ImplPlayer sender)
    {

    }

    public void convene(ImplPlayer paramSender, ImplPlayer paramPlayer)
    {

    }

    public void broadcast(String paramString)
    {

    }
}
