package io.myosotisdev.minestom.command;

import io.myosotisdev.minestom.command.permission.*;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;

/**
 * command 'grant' is used to grant permission to a specific unit that can hold permissions, such as Player, but also
 * any created permission groups.
 *
 * @author RainbowMeowCat
 */
public class PermissionCommand
        extends AbstractCommand
{
    public static final String Name = "permission";
    public static final String[] Aliases = new String[]{
            "perm",
    };

    public PermissionCommand()
    {
        super(null, Name, Aliases);

        this.addSubcommand(new AddSubCommand(this));
        this.addSubcommand(new RemoveSubCommand(this));
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "permission");
    }
}
