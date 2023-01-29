package io.myosotisdev.utopianism.serialize

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot

class EquipSlotTagSerializer : Serializer<ItemEquipSlot>()
{
    override fun serialize(obj: ItemEquipSlot?): String?
    {
        return null
    }

    override fun deserialize(string: String?): ItemEquipSlot?
    {
        return null
    }
}