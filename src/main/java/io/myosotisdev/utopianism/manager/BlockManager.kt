package io.myosotisdev.utopianism.manager

import io.myosotisdev.utopianism.modules.item.ItemStackTemplate
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.item.Material

class BlockManager
{
    val blockDrops: HashMap<String, Array<ItemStackTemplate>> = HashMap()

    fun handleBlockBehavior(event: PlayerBlockBreakEvent)
    {

    }

    fun setBlockDrop(material: Material, itemStackTemplates: Array<ItemStackTemplate>)
    {
        blockDrops[material.toString().lowercase()] = itemStackTemplates
    }
}