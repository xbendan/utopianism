package io.myosotisdev.utopianism.battle

class ShieldMeta(val source: String?, var type: DamageModifier.DamageType?, private val beginValue: Int)
{
    var value: Int = beginValue
    val isBreakRemove = false

    fun isAcceptable(damage: DamageModifier.DamageType): Boolean
    {
        return type == null || damage === type
    }

    fun cancel()
    {
        /**
         * if(task != null)
         * {
         * task.cancel();
         * }
         */
    }

    /**
     * @param value
     * @return damage remained
     */
    fun reduce(value: Int): Int
    {
        return if (value > beginValue) value - beginValue
        else
        {
            this.value -= value
            0
        }
    }
}