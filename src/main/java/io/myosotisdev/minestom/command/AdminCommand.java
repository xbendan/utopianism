package io.myosotisdev.minestom.command;

import io.myosotisdev.minestom.command.admin.BackpackCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;

public class AdminCommand extends AbstractCommand
{
    public static final String   Name    = "administrator";
    public static final String[] Aliases = new String[]
            {
                    "admin",
            };

    public AdminCommand()
    {
        super(null, Name, Aliases);

        addSubcommand(new BackpackCommand(this));
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "admin");
    }
}
