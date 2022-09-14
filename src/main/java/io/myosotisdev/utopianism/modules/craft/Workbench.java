package io.myosotisdev.utopianism.modules.craft;

import io.myosotisdev.utopianism.Locales;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;

public class Workbench
        extends CraftingStation
{
    static final String WorkbenchKey = "workbench";

    public Workbench(boolean shared, Pos pos)
    {
        super(WorkbenchKey, shared, pos);
    }

    public Workbench(String key, boolean shared, Pos pos, String recipesKey)
    {
        super(key, shared, pos, recipesKey);
    }

    @Override
    public Worker createWorker(Player player)
    {
        return new Workbench.Worker(this, player);
    }

    public static class Worker
            extends CraftingStation.Worker
    {
        public Worker(CraftingStation station, Player player)
        {
            super(station, new Inventory(InventoryType.WINDOW_3X3, Locales.get(player.getLocale(), WorkbenchKey + ".title")));
        }
    }
}
