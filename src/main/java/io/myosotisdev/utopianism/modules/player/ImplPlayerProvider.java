package io.myosotisdev.utopianism.modules.player;

import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ImplPlayerProvider implements PlayerProvider
{
    @Override
    public @NotNull Player createPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection connection)
    {
        return new ImplPlayer(uuid, username, connection);
    }
}
