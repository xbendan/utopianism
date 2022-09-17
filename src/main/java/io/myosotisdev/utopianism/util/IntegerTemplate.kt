package io.myosotisdev.utopianism.util

import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

class IntegerTemplate @JvmOverloads constructor(private val base: Int, private val useProportion: Boolean = true, private val useValues: Boolean = true) : INumberTemplate<Int>
{
    private val proportion: ConcurrentHashMap<String, ValueDouble> = ConcurrentHashMap()
    private val values: ConcurrentHashMap<String, ValueInt> = ConcurrentHashMap()
    private val updated = false
    private var latest = 0

    override fun addProportion(key: String?, percentage: Double, duration: Int)
    {
    }

    override fun addValue(key: String?, value: Int, duration: Int)
    {
        TODO("Not yet implemented")
    }

    override fun removeProportion(key: String?)
    {
        TODO("Not yet implemented")
    }

    override fun removeValue(key: String?)
    {
        TODO("Not yet implemented")
    }

    override fun value(): Int
    {
        return if (updated) (base
                             + (if (useProportion) BigDecimal(base * proportion.values.stream()
                .mapToDouble { obj: ValueDouble -> obj.value }
                .reduce(0.0) { a: Double, b: Double -> java.lang.Double.sum(a, b) }).toInt()
        else 0)
                             + if (useValues) values.values.stream()
                .mapToInt { obj: ValueInt -> obj.value }
                .reduce(0) { a: Int, b: Int -> Integer.sum(a, b) }
                             else 0).also { latest = it }
        else latest
    }
}