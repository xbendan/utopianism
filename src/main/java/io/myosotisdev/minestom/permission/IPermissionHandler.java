package io.myosotisdev.minestom.permission;

import java.util.Collection;

public interface IPermissionHandler
{
    void addPermission(Permission perm);

    void removePermission(Permission perm);

    boolean hasPermission(Permission perm);

    Collection<Permission> permissions();
}
