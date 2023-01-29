package io.myosotisdev.utopianism.util

class ValueDouble(val source: String?, val value: Double)
{

    fun cancel()
    {
    }

    val isNeverOutdated: Boolean
        get() = true

    override fun equals(obj: Any?): Boolean
    {
        return obj is ValueDouble && obj.value == value
    }
}