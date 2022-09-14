package io.myosotisdev.minestom.command.admin;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.command.AbstractCommand;
import io.myosotisdev.minestom.permission.Permission;
import io.myosotisdev.minestom.util.command.Commands;
import io.myosotisdev.utopianism.util.Namespaces;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackCommand
        extends AbstractCommand
{
    public static final String   Name    = "backpack";
    public static final String[] Aliases = new String[]
            {
                    "pack",
                    "bp"
            };

    public BackpackCommand(AbstractCommand command)
    {
        super(command, Name, Aliases);
        
        addSubcommand(new View());
    }

    @Override
    public Permission permission()
    {
        return Permission.ofString(Namespaces.Minestom, "admin.backpack");
    }

    class View
            extends AbstractCommand
    {
        public static final String Name = "view";

        private final ArgumentEntity arg0_player = ArgumentType.Entity("player")
                                                               .singleEntity(true)
                                                               .onlyPlayers(true);

        public View()
        {
            super(BackpackCommand.this, Name);
            
            addConditionalSyntax(Commands.createWithPermission(permission()), (sender, context) -> {
                Player player = context.get(arg0_player).findFirstPlayer(null);
                if(player != null)
                {
                    Inventory viewInventory = new Inventory(InventoryType.CHEST_4_ROW, player.getName());
                    PlayerInventory originInventory = player.getInventory();

                    for (int slotId = 0; slotId < 36; slotId++)
                        viewInventory.setItemStack(slotId, originInventory.getItemStack(slotId));
                    
                    viewInventory.setTag(Tag.Boolean("inventory_clickable"), false);
                    player.openInventory(viewInventory);
                }
                else
                    sender.sendMessage(Component.text("Player does not exist!", NamedTextColor.RED));
            }, arg0_player);
        }

        @Override
        public Permission permission()
        {
            return getParent().permission();
        }
    }
    
    class Clear extends AbstractCommand
    {
        public static final String Name = "view";

        private final ArgumentEntity arg0_player = ArgumentType.Entity("player")
                                                               .singleEntity(true)
                                                               .onlyPlayers(true);

        public Clear(AbstractCommand command, @NotNull String name, @Nullable String... aliases)
        {
            super(command, name, aliases);
        }

        @Override
        public Permission permission()
        {
            return getParent().permission();
        }
    }
}
