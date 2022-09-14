package io.myosotisdev.minestom.command.permission;

import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;

public class AddSubCommand
        extends AbstractCommand
{
    public static final String Name = "add";
    public static final String[] Aliases = new String[]{
            "a",
            "grant"
    };

    private final ArgumentString arg1 = ArgumentType.String("player|group");
    private final ArgumentString arg2 = ArgumentType.String("name");
    private final ArgumentString arg3 = ArgumentType.String("permission");

    public AddSubCommand(AbstractCommand parent)
    {
        super(parent, Name, Aliases);

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("add");
        });
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "permission.grant");
    }
}
