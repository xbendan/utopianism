package io.myosotisdev.minestom.command;

import com.google.common.base.Strings;
import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.utopianism.util.Namespaces;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GameModeCommand
        extends AbstractCommand
{
    public static final String                 Name    = "gamemode";
    public static final String[]               Aliases = new String[]
            {
                    "gm"
            };
    private             ArgumentEnum<GameMode> arg0;
    private             ArgumentEntity         arg1;

    public GameModeCommand()
    {
        super(null, Name, Aliases);

        this.arg0 = ArgumentType.Enum("gamemode", GameMode.class);
        this.arg1 = ArgumentType.Entity("player")
                                .onlyPlayers(true)
                                .singleEntity(true);

        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
            {
                sender.sendMessage(Component.text("You don't have enough permission to do that", NamedTextColor.RED));
                return;
            }

            if (sender instanceof ConsoleSender)
            {
                sender.sendMessage(Component.text("This command could only be used by player!", NamedTextColor.RED));
                return;
            }

            GameMode newMode = context.get(arg0);

            ((Player) sender).setGameMode(newMode);
            sender.sendMessage("Your gamemode has been set to " + newMode.name());
        }, arg0);
        addSyntax((sender, context) -> {
            if (!Minestom.checkPermission(sender, permission()))
            {
                sender.sendMessage(Component.text("You don't have enough permission to do that", NamedTextColor.RED));
                return;
            }

            Player player = context.get(arg1)
                                   .findFirstPlayer(null);
            if (player != null)
            {
                GameMode newMode = context.get(arg0);

                player.setGameMode(newMode);
                player.sendMessage("Your gamemode has been set to " + newMode.name());
            }
            else
                sender.sendMessage(Component.text("This command could only be used by player!", TextColor.color(255, 0, 0)));
        }, arg0, arg1);
    }

    void defaultExecutor(CommandSender sender, GameMode gameMode, Player player)
    {
        if (player == null)
        {
            if (sender instanceof ConsoleSender)
            {
                sender.sendMessage(Component.text("This command could only be used by player!", TextColor.color(255, 0, 0)));
                return;
            }
            else if (sender instanceof Player)
                player = ((Player) sender);
            else
            {
                sender.sendMessage("Unspecified sender type.");
                return;
            }
        }

        player.sendMessage("Your gamemode has been set to " + gameMode.name());
        player.setGameMode(gameMode);
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minecraft, "gamemode");
    }
}
