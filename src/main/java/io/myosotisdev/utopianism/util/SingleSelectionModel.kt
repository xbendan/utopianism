package io.myosotisdev.utopianism.util

class SingleSelectionModel<K, V>
{
    val models: HashMap<K, V> = HashMap()
    private var _hidden: K? = null
    var currentKey: K?
        get() = _hidden
        set(value)
        {
            _hidden = value
            current = models[value]
        }
    var current: V? = null

    operator fun set(key: K, value: V)
    {
        models[key] = value
    }

    fun addIfAbsent(key: K, value: V)
    {
        models.putIfAbsent(key, value)
    }

    operator fun get(key: K): V?
    {
        return models[key]
    }

    fun containsKey(key: K): Boolean
    {
        return models.containsKey(key)
    }

    fun containsValue(value: V): Boolean
    {
        return models.containsValue(value)
    }

    fun keys(): Set<K>
    {
        return models.keys
    }

    fun values(): Collection<V>
    {
        return models.values
    }
}