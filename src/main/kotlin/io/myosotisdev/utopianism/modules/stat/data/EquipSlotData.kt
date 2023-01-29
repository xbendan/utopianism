package io.myosotisdev.utopianism.modules.stat.data

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot
import io.myosotisdev.utopianism.serialize.EquipSlotTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer

class EquipSlotData(override var value: ItemEquipSlot = ItemEquipSlot.ALL) : ItemStatData<ItemEquipSlot>
{
    companion object
    {
        var Serializer: Serializer<ItemEquipSlot> = EquipSlotTagSerializer()
    }
}