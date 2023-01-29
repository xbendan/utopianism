package io.myosotisdev.utopianism.modules.stat.data

class ListData<T> : ItemStatData<List<T>>
{
    override var value: List<T>
        get() = ArrayList()
        set(value)
        {
        }
}