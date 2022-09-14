package io.myosotisdev.minestom;

import io.myosotisdev.minestom.entity.PlayerEx;
import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerProviderEx
        implements PlayerProvider
{
    @Override
    public @NotNull Player createPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection connection)
    {
        return new PlayerEx(uuid, username, connection);
    }
}
