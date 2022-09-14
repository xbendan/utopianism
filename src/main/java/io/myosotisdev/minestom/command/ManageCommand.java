package io.myosotisdev.minestom.command;

import io.myosotisdev.minestom.command.manage.OperatorSubCommand;
import io.myosotisdev.minestom.command.manage.ServerSubCommand;
import io.myosotisdev.minestom.command.manage.WorldSubCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;

public class ManageCommand extends AbstractCommand
{
    public static final String Name = "manage";
    public static final String[] Aliases = new String[]{
            "man",
            "mg"
    };

    public ManageCommand()
    {
        super(null, Name, Aliases);

        this.addSubcommand(new OperatorSubCommand(this));
        this.addSubcommand(new ServerSubCommand(this));
        this.addSubcommand(new WorldSubCommand(this));
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "manage");
    }
}
