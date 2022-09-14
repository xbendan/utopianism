package io.myosotisdev.minestom.util.command;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.permission.Permission;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.condition.CommandCondition;

public class Commands
{
    public static CommandCondition createWithPermission(Permission permission)
    {
        return (sender, commandString) -> !(sender instanceof ConsoleSender) && Minestom.checkPermission(sender, permission);
    }
}
