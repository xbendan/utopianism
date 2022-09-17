package io.myosotisdev.utopianism.modules.stat.data

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot
import io.myosotisdev.utopianism.serialize.EquipSlotTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer

class EquipSlotData : ItemStatData<ItemEquipSlot>
{
    override var value: ItemEquipSlot = ItemEquipSlot.ALL

    companion object
    {
        var Serializer: Serializer<ItemEquipSlot> = EquipSlotTagSerializer()
    }
}