package io.myosotisdev.minestom.permission

import io.myosotisdev.minestom.entity.PlayerEx
import net.minestom.server.entity.Player
import net.minestom.server.utils.NamespaceID

/**
 * The basic class of permission control
 * For instance, a player is allowed to use the command `/ut item give <player> <item>` if they or their group
 * has any permissions below:
 * - `utopianism:item.give.other`
 * - `utopianism:item.give.*`
 * - `utopianism:item.*`
 * - `utopianism:*`
 * - `utopianism:admin`
 * The permission name is not case-sensitive, all string will be converted as lower case.
 * This system always prefer to check the permission of group where player stays at. The order should be:
 * - Is permission handler using console?
 * - Is permission handler an operator? (Op)
 * - Does permission group where sender at has matched permission?
 * - Does permission handler has matched permission?
 */
class Permission protected constructor(private val full: String, private val domain: String, vararg nodes: String) : net.minestom.server.permission.Permission(full)
{
    private var nodes: Array<String>

    init
    {
        this.nodes = arrayOf(*nodes)
    }

    fun matchWith(perm: Permission): Boolean
    {
        if (domain !== perm.domain || perm.nodeLength() < nodes.size) return false
        for (i in nodes.indices)
        {
            if (nodes[i] === "*") return true else if (nodes[i] !== perm.nodeAt(i)) return false
        }
        return true
    }

    fun domain(): String
    {
        return domain
    }

    fun nodes(): Array<String>
    {
        return nodes
    }

    fun nodeAt(index: Int): String?
    {
        return if (index < nodes.size) nodes[index] else null
    }

    fun nodeLength(): Int
    {
        return nodes.size
    }

    override fun equals(obj: Any?): Boolean
    {
        return this.toString() === obj.toString()
    }

    override fun toString(): String
    {
        return full
    }

    override fun getPermissionName(): String
    {
        return full
    }

    fun Player.removePerm(permission: Permission?)
    {
        (this as PlayerEx).removePerm(permission)
    }

    fun Player.addPerm(permission: Permission?)
    {
        (this as PlayerEx).addPerm(permission)
    }

    companion object
    {
        private val LegalDomainLetters: Regex = Regex("[0123456789abcdefghijklmnopqrstuvwxyz-]+")
        private val LegalPathLetters: Regex = Regex("[0123456789abcdefghijklmnopqrstuvwxyz.*-]+")

        fun ofString(string: String): Permission?
        {
            val colonIndex = string.indexOf(58.toChar())
            return if (colonIndex <= 0 || string.endsWith(":")) null
            else
            {
                val domain = string.substring(0, colonIndex)
                val nodepath = string.substring(colonIndex + 1)
                if (domain.matches(LegalDomainLetters) && nodepath.matches(LegalPathLetters)) Permission(string, domain, *nodepath.split(".".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray())
                else null
            }
        }

        @JvmStatic
        fun ofString(domain: String, path: String): Permission
        {
            return Permission("$domain:$path", domain, *path.split(".".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray())
        }

        fun ofNamespace(namespace: NamespaceID, path: String): Permission
        {
            return ofString(namespace.domain(), path)
        }
    }
}