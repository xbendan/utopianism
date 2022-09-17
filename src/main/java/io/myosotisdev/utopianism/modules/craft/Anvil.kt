package io.myosotisdev.utopianism.modules.craft

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class Anvil : CraftingStation
{
    constructor(isShared: Boolean, position: Pos) : super("anvil", isShared, position)
    {
    }

    constructor(shared: Boolean, pos: Pos, recipesKey: String?) : super("anvil", shared, pos, recipesKey)
    {
    }

    override fun createWorker(player: Player?): Worker
    {
        return CraftingStation.Worker(this, Inventory(InventoryType.ANVIL, ""))
    }
}