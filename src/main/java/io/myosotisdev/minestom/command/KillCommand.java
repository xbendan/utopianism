package io.myosotisdev.minestom.command;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KillCommand
        extends AbstractCommand
{
    public static final String   Name    = "kill";
    public static final String[] Aliases = new String[0];

    private final ArgumentEntity arg0_player = ArgumentType.Entity("player")
                                                           .onlyPlayers(true)
                                                           .singleEntity(true);

    public KillCommand()
    {
        super(null, Name);

        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
                sender.sendMessage(Component.text("You don't have enough permission to do that!", TextColor.color(255, 0, 0)));
            else
            {
                if (sender instanceof ConsoleSender)
                    return;

                ((Player) sender).kill();
            }
        });

        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
                sender.sendMessage(Component.text("You don't have enough permission to do that!", TextColor.color(255, 0, 0)));
            else
            {
                Player player = context.get(arg0_player)
                                       .findFirstPlayer(null);
                if(player != null)
                {
                    player.kill();
                    sender.sendMessage(Component.textOfChildren(
                            player.getName(),
                            Component.text(" has been killed.")
                    ));
                }
            }

        }, arg0_player);
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minecraft, "kill");
    }
}
