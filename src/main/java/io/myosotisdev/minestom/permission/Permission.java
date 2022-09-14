package io.myosotisdev.minestom.permission;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

/**
 * The basic class of permission control
 * For instance, a player is allowed to use the command {@code /ut item give <player> <item>} if they or their group
 * has any permissions below:
 * - {@code utopianism:item.give.other}
 * - {@code utopianism:item.give.*}
 * - {@code utopianism:item.*}
 * - {@code utopianism:*}
 * - {@code utopianism:admin}
 * The permission name is not case-sensitive, all string will be converted as lower case.
 * This system always prefer to check the permission of group where player stays at. The order should be:
 * - Is permission handler using console?
 * - Is permission handler an operator? (Op)
 * - Does permission group where sender at has matched permission?
 * - Does permission handler has matched permission?
 */
public class Permission
        extends net.minestom.server.permission.Permission
{
    private static final String LegalDomainLetters = "[0123456789abcdefghijklmnopqrstuvwxyz-]+";
    private static final String LegalPathLetters   = "[0123456789abcdefghijklmnopqrstuvwxyz.*-]+";

    private String   full;
    private String   domain;
    private String[] nodes;

    protected Permission(String full, String domain, String... nodes)
    {
        super(full);
        this.full = full;
        this.domain = domain;
        this.nodes = nodes;
    }

    public boolean matchWith(Permission perm)
    {
        if (this.domain != perm.domain || perm.nodeLength() < nodes.length)
            return false;

        for (int i = 0; i < nodes.length; i++)
        {
            if (nodes[i] == "*")
                return true;
            else if (nodes[i] != perm.nodeAt(i))
                return false;
        }

        return true;
    }

    public String domain()
    {
        return this.domain;
    }

    public String[] nodes()
    {
        return this.nodes;
    }

    public String nodeAt(int index)
    {
        return index < nodes.length ?
                nodes[index] :
                null;
    }

    public int nodeLength()
    {
        return this.nodes.length;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.toString() == obj.toString();
    }

    @Override
    public String toString()
    {
        return this.full;
    }

    @Override
    public @NotNull String getPermissionName()
    {
        return this.full;
    }

    public static Permission ofString(String string)
    {
        int colonIndex = string.indexOf(58);
        if (colonIndex <= 0 || string.endsWith(":"))
            return null;
        else
        {
            String domain = string.substring(0, colonIndex);
            String nodepath = string.substring(colonIndex + 1);
            return domain.matches(LegalDomainLetters) && nodepath.matches(LegalPathLetters) ?
                    new Permission(string, domain, nodepath.split(".")) :
                    null;
        }
    }

    public static Permission ofString(String domain, String path)
    {
        return new Permission(domain + ":" + path, domain, path.split("."));
    }

    public static Permission ofNamespace(NamespaceID namespace, String path)
    {
        return ofString(namespace.domain(), path);
    }
}
