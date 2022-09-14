package io.myosotisdev.utopianism.modules.stat.data;

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot;
import io.myosotisdev.utopianism.serialize.EquipSlotTagSerializer;
import io.myosotisdev.utopianism.serialize.Serializer;

public class EquipSlotData implements ItemStatData<ItemEquipSlot>
{
    public static Serializer<ItemEquipSlot> Serializer = new EquipSlotTagSerializer();

    private ItemEquipSlot value;

    @Override
    public ItemEquipSlot getData()
    {
        return this.value;
    }
}
