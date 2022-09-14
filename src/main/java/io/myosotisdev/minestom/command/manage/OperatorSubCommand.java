package io.myosotisdev.minestom.command.manage;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.arguments.ArgumentBoolean;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class OperatorSubCommand
        extends AbstractCommand
{
    public static final String   Name    = "operator";
    public static final String[] Aliases = new String[]{
            "op",
            "administrator",
            "admin"
    };

    private ArgumentString  arg0_playerName = ArgumentType.String("name");
    private ArgumentBoolean arg1_enable     = ArgumentType.Boolean("enable");

    public OperatorSubCommand(AbstractCommand parent)
    {
        super(parent, Name, Aliases);

        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
                sender.sendMessage("You have no permission to do that!");
            else
            {
                if (sender instanceof Player)
                    Minestom.setOp((Player) sender, context.get(arg1_enable));
                else
                    sender.sendMessage("This command could only be used by player.");
            }
        }, arg1_enable);

        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
                sender.sendMessage("You have no permission to do that!");
            else
            {
                Player player = Minestom.getPlayer(context.get(arg0_playerName));
                if (player != null)
                {
                    boolean opEnable = context.get(arg1_enable);
                    Minestom.setOp(player, opEnable);
                    sender.sendMessage(Component.textOfChildren(player.getName(), Component.text(opEnable ?
                            " has been set to operator." :
                            " is not operator any more.")));
                }
                else
                    sender.sendMessage("Player does not exist.");
            }
        }, arg0_playerName, arg1_enable);
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "manage.operator");
    }
}
