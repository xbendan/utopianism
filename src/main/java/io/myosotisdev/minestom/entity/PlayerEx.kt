package io.myosotisdev.minestom.entity

import io.myosotisdev.minestom.permission.IPermissionHandler
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.minestom.permission.PermissionGroup
import net.minestom.server.entity.Player
import net.minestom.server.network.player.PlayerConnection
import java.util.*

open class PlayerEx(uuid: UUID, username: String, playerConnection: PlayerConnection) : Player(uuid, username, playerConnection),
                                                                                        IPermissionHandler
{
    private val permissions: MutableSet<Permission?>
    private val permGroup: PermissionGroup? = null

    init
    {
        this.permissions = HashSet()
    }

    override fun hasPermission(permission: net.minestom.server.permission.Permission): Boolean
    {
        return permission is Permission && hasPerm(permission)
    }

    override fun hasPermission(permissionName: String): Boolean
    {
        return this.hasPerm(ofString(permissionName))
    }

    override fun addPerm(permission: Permission?)
    {
        this.permissions.add(permission)
    }

    override fun removePerm(permission: Permission?)
    {
        this.permissions.remove(permission)
    }

    override fun hasPerm(permission: Permission?): Boolean
    {
        return permGroup?.hasPerm(permission) ?: false ||
               permissions.stream()
                       .anyMatch { perm: Permission? -> perm!!.matchWith(permission!!) }
    }

    override fun permissions(): Collection<Permission?>?
    {
        return permissions
    }
}