package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.utopianism.Locales
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class SmeltingFurnace : CraftingStation
{
    constructor(isShared: Boolean, position: Pos) : super("smelting_furnace", isShared, position)
    {
    }

    constructor(shared: Boolean, pos: Pos, recipesKey: String?) : super("smelting_furnace", shared, pos, recipesKey)
    {
    }

    override fun createWorker(player: Player?): Worker
    {
        return CraftingStation.Worker(this, Inventory(InventoryType.CHEST_6_ROW, Locales.translate(player!!.locale, "utopianism:craft.smelting_furnace.title")))
    }
}