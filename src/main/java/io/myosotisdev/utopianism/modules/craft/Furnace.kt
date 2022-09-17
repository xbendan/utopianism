package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.utopianism.Locales
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class Furnace : CraftingStation
{
    constructor(shared: Boolean, position: Pos) : super("furnace", shared, position)
    {
    }

    constructor(shared: Boolean, pos: Pos, recipesKey: String?) : super("furnace", shared, pos, recipesKey)
    {
    }

    override fun createWorker(player: Player?): Worker
    {
        return CraftingStation.Worker(this, Inventory(InventoryType.FURNACE, Locales.translate(player!!.locale, "utopianism:craft.furnace.title")))
    }
}