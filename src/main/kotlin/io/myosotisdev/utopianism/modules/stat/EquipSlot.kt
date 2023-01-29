package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.item.ItemEquipSlot
import io.myosotisdev.utopianism.modules.stat.data.EquipSlotData
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class EquipSlot : ItemStat<ItemEquipSlot>("equip-slot", Tags.createTag("equip-slot", EquipSlotData.Serializer))
{
    override fun defaultStatData(): ItemStatData<ItemEquipSlot> = EquipSlotData()

    override fun createStatData(jsonObject: JsonObject): ItemStatData<ItemEquipSlot> = EquipSlotData(ItemEquipSlot.valueOf(jsonObject.asString.uppercase()))
}