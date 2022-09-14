package io.myosotisdev.utopianism.command;

import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;

public class UtopianismMainCommand extends AbstractCommand
{
    public static final String   Name    = "utopianism";
    public static final String[] Aliases = new String[]
    {
            "ut"
    };

    public UtopianismMainCommand()
    {
        super(null, Name, Aliases);

        addSubcommand(new GuildCommand(this));
    }

    @Override
    public Permission permission()
    {
        return null;
    }
}
