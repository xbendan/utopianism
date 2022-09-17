package io.myosotisdev.utopianism.util

interface INumberTemplate<T : Number>
{
    fun addProportion(key: String?, percentage: Double, duration: Int)
    fun addValue(key: String?, value: T, duration: Int)
    fun removeProportion(key: String?)
    fun removeValue(key: String?)
    fun value(): T
}