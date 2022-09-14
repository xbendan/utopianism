package io.myosotisdev.minestom.command.manage;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;
import net.minestom.server.entity.Player;

public class WorldSubCommand
        extends AbstractCommand
{
    public static final String   Name    = "world";
    public static final String[] Aliases = new String[0];

    public WorldSubCommand(AbstractCommand command)
    {
        super(command, Name);

        addSyntax((sender, context) -> {
            if(!Minestom.checkPermission(sender, permission()))
                sender.sendMessage("You cannot do that.");
            else
            {
                if(sender instanceof Player)
                {
                    sender.sendMessage(((Player) sender).getInstance().toString());
                }
            }
        });
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minecraft, "world");
    }
}
