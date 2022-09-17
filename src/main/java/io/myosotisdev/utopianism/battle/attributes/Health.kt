package io.myosotisdev.utopianism.battle.attributes

import com.google.common.primitives.Doubles
import io.myosotisdev.utopianism.battle.ShieldMeta
import io.myosotisdev.utopianism.util.IntegerTemplate
import net.minestom.server.entity.LivingEntity

class Health
{
    val entity: LivingEntity? = null
    val maxValue: IntegerTemplate? = null
    val regen: IntegerTemplate? = null
    var actualHealth = 0.0
        set(value)
        {
            field = Doubles.constrainToRange(value, 0.0, maxValue!!.value()
                    .toDouble())
        }
    val shields: List<ShieldMeta>? = null
}