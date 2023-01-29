package io.myosotisdev.utopianism.util

class ValueInt(val source: String?, val value: Int)
{

    fun cancel()
    {
    }

    //taskControl == null;
    val isNeverOutdated: Boolean
        get() = true //taskControl == null;

    override fun equals(obj: Any?): Boolean
    {
        return obj is ValueInt && obj.value == value
    }
}