package io.myosotisdev.minestom.permission;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PermissionGroup implements IPermissionHandler
{
    private String          groupId;
    private Set<Permission> permissions;

    public PermissionGroup(String groupId)
    {
        this.groupId = groupId;
        this.permissions = new HashSet<>();
    }

    public String getId()
    {
        return this.groupId;
    }

    @Override
    public void addPermission(Permission perm)
    {
        this.permissions.add(perm);
    }

    @Override
    public void removePermission(Permission perm)
    {
        this.permissions.remove(perm);
    }

    @Override
    public boolean hasPermission(Permission perm)
    {
        return this.permissions.stream().anyMatch(permission -> permission.matchWith(perm));
    }

    @Override
    public Collection<Permission> permissions()
    {
        return this.permissions;
    }
}
