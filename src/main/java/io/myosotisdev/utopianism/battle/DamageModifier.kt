package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.util.IntegerTemplate

class DamageModifier @JvmOverloads constructor(val source: String, val type: DamageType, value: Int, ignoreShield: Boolean = false)
{
    val template: IntegerTemplate
    val isIgnoreShield: Boolean

    init
    {
        template = IntegerTemplate(value)
        isIgnoreShield = ignoreShield
    }

    fun getValue(): Int
    {
        return template.value()
    }

    fun ignoreShield(): Boolean
    {
        return isIgnoreShield
    }

    enum class DamageType
    {
        PHYSIC,
        MAGIC,
        TRUE
    }
}