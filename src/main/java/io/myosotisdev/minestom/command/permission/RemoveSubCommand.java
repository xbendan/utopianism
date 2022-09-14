package io.myosotisdev.minestom.command.permission;

import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;

public class RemoveSubCommand extends AbstractCommand
{
    public static final String Name = "remove";
    public static final String[] Aliases = new String[]{
            "r",
            "rm",
            "delete",
            "del"
    };

    public RemoveSubCommand(AbstractCommand parent)
    {
        super(parent, Name, Aliases);
    }

    @Override
    public Permission permission()
    {
        return null;
    }
}
