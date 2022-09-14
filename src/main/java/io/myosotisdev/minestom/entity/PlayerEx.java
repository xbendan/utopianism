package io.myosotisdev.minestom.entity;

import io.myosotisdev.minestom.permission.IPermissionHandler;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.minestom.permission.PermissionGroup;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.permission.PermissionVerifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerEx
        extends Player
        implements IPermissionHandler
{
    private Set<Permission> permissions;
    private PermissionGroup permGroup;

    public PlayerEx(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection)
    {
        super(uuid, username, playerConnection);
        this.permissions = new HashSet<>();
    }

    @Override
    public boolean hasPermission(@NotNull net.minestom.server.permission.Permission permission)
    {
        return permission instanceof Permission && hasPermission((Permission) permission);
    }

    @Deprecated
    @Override
    public boolean hasPermission(@NotNull String permissionName, @Nullable PermissionVerifier permissionVerifier)
    {
        return this.hasPermission(permissionName);
    }

    @Override
    public boolean hasPermission(@NotNull String permissionName)
    {
        return this.hasPermission(Permission.ofString(permissionName));
    }

    @Override
    public void addPermission(Permission permission)
    {
        this.permissions.add(permission);
    }

    @Override
    public void removePermission(Permission permission)
    {
        this.permissions.remove(permission);
    }

    @Override
    public boolean hasPermission(Permission permission)
    {
        return (permGroup != null ?
                permGroup.hasPermission(permission) :
                false) ||
                permissions.stream()
                           .anyMatch(perm -> perm.matchWith(permission));
    }

    @Override
    public Collection<Permission> permissions()
    {
        return permissions;
    }
}
