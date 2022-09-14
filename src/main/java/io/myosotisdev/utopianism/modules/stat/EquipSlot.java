package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.item.ItemEquipSlot;
import io.myosotisdev.utopianism.modules.stat.data.EquipSlotData;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.util.Tags;
import net.minestom.server.item.ItemStack;

public class EquipSlot
        extends ItemStat<ItemEquipSlot>
{
    public EquipSlot()
    {
        super("equip-slot", Tags.createTag("equip-slot", EquipSlotData.Serializer));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return null;
    }
}
