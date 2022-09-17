package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot
import io.myosotisdev.utopianism.modules.stat.data.EquipSlotData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class EquipSlot : ItemStat<ItemEquipSlot>("equip-slot", Tags.createTag("equip-slot", EquipSlotData.Serializer) as Tag<ItemEquipSlot>)
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in ItemEquipSlot>)
    {
        return
    }

    override fun defaultStatData(): ItemStatData<ItemEquipSlot>?
    {
        return null
    }
}