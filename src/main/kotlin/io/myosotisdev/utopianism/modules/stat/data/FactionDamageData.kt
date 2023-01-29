package io.myosotisdev.utopianism.modules.stat.data

import io.myosotisdev.utopianism.modules.faction.FactionDamage
import io.myosotisdev.utopianism.serialize.FactionDamageTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer

class FactionDamageData(override var value: FactionDamage) : ItemStatData<FactionDamage>
{
    companion object
    {
        val Serializer: Serializer<FactionDamage> = FactionDamageTagSerializer()
    }
}
