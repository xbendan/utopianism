package io.myosotisdev.minestom.module;

import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PermissionModule
        extends Module
{
    private Set<UUID> operators;

    public PermissionModule()
    {
        super("permission");
        this.operators = new HashSet<>();
    }

    public void setOp(Player player, boolean enable)
    {
        if(player == null)
            return;

        if(enable)
        {
            operators.add(player.getUuid());
            player.sendMessage("You have been set to operator.");
        }
        else
        {
            operators.remove(player.getUuid());
            player.sendMessage("You are not operator any more");
        }
    }

    public boolean isOp(CommandSender sender)
    {
        return (sender instanceof Player) && isOp((Player) sender);
    }

    public boolean isOp(Player player)
    {
        return operators.contains(player.getUuid());
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }
}
