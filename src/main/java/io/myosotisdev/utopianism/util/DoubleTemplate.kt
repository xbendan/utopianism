package io.myosotisdev.utopianism.util

import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

class DoubleTemplate(private val base: Double, private val useProportion: Boolean = true, private val useValues: Boolean = true) : INumberTemplate<Double>
{
    private val proportion: ConcurrentHashMap<String, ValueDouble> = ConcurrentHashMap()
    private val values: ConcurrentHashMap<String, ValueDouble> = ConcurrentHashMap()
    private val updated = false
    private var latest: Double = 0.0

    override fun addProportion(key: String?, percentage: Double, duration: Int)
    {
    }

    override fun addValue(key: String?, value: Double, duration: Int)
    {
    }

    override fun removeProportion(key: String?)
    {
        TODO("Not yet implemented")
    }

    override fun removeValue(key: String?)
    {
        TODO("Not yet implemented")
    }

    override fun value(): Double
    {
        return if (updated) (base
                             + (if (useProportion) BigDecimal(base * proportion.values.stream()
                .mapToDouble { obj: ValueDouble -> obj.value }
                .reduce(0.0) { a: Double, b: Double -> java.lang.Double.sum(a, b) }).toDouble()
        else 0.0)
                             + if (useValues) values.values.stream()
                .mapToDouble { obj: ValueDouble -> obj.value }
                .reduce(0.0) { a: Double, b: Double -> a + b }
                             else 0.0).also { latest = it }
        else latest
    }
}