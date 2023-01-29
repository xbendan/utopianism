package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.serialize.Serializer
import java.util.*

class BattleStateTagSerializer : Serializer<BattleState>()
{
    override fun serialize(obj: BattleState?): String? = obj?.toString()

    override fun deserialize(string: String?): BattleState? = BattleState.values()
            .firstOrNull { Objects.equals(it.toString(), string) }
}