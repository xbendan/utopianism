package io.myosotisdev.minestom.command.manage;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;

public class ServerSubCommand extends AbstractCommand
{
    public static final String Name = "server";
    public static final String[] Aliases = new String[0];

    public ServerSubCommand(AbstractCommand parent)
    {
        super(parent, Name);

        this.addSubcommand(new Stop(this));
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "manage.server");
    }

    static class Stop extends AbstractCommand
    {
        static final String Name = "stop";

        public Stop(AbstractCommand command)
        {
            super(command, Name);

            addSyntax((sender, context) -> {
                if(!Minestom.checkPermission(sender, permission()))
                    sender.sendMessage("You don't have permission to do that!");
                else
                {
                    Minestom.stop();
                }
            });
        }

        @Override
        public Permission permission()
        {
            return getParent().permission();
        }
    }
}
