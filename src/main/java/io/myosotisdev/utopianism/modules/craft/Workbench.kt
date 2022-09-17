package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.utopianism.Locales
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class Workbench : CraftingStation
{
    constructor(shared: Boolean, pos: Pos?) : super("workbench", shared, pos!!)
    {
    }

    constructor(shared: Boolean, pos: Pos?, recipesKey: String?) : super("workbench", shared, pos!!, recipesKey)
    {
    }

    override fun createWorker(player: Player?): Worker
    {
        return Worker(this, Inventory(InventoryType.WINDOW_3X3, Locales.translate(player!!.locale, "utopianism:craft.workbench.title")))
    }
}