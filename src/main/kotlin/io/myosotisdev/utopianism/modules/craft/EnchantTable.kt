package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.utopianism.Locales
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class EnchantTable : CraftingStation
{
    constructor(isShared: Boolean, position: Pos) : super("enchant_table", isShared, position)
    {
    }

    constructor(shared: Boolean, pos: Pos, recipesKey: String?) : super("enchant_table", shared, pos, recipesKey)
    {
    }

    override fun createWorker(player: Player?): Worker
    {
        return Worker(this, Inventory(InventoryType.CHEST_6_ROW, Locales.translate(player, Namespaces.Utopianism + ":craft.enchant_table.title")))
    }
}