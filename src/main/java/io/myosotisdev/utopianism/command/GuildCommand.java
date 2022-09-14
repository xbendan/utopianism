package io.myosotisdev.utopianism.command;

import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;

public class GuildCommand
        extends AbstractCommand
{
    public static final String   Name    = "guild";
    public static final String[] Aliases = new String[0];

    public GuildCommand(AbstractCommand command)
    {
        super(command, Name, Aliases);

        addSyntax((sender, context) -> {

        });

        addSubcommand(new Create(this));
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Utopianism, "guild");
    }

    class Create
            extends AbstractCommand
    {
        public static final String Name = "create";

        public Create(AbstractCommand command)
        {
            super(command, Name);
        }

        @Override
        public Permission permission()
        {
            return Permission.ofString(Namespaces.Utopianism, "guild.create");
        }
    }

    class Dissolve extends AbstractCommand
    {
        public static final String Name = "dissolve";

        public Dissolve(AbstractCommand command)
        {
            super(command, Name);
        }

        @Override
        public Permission permission()
        {
            return Permission.ofString(Namespaces.Utopianism, "guild.dissolve");
        }
    }
}
