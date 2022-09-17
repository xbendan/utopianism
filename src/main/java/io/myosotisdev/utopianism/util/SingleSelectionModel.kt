package io.myosotisdev.utopianism.util

class SingleSelectionModel<K, V>
{
    val models: HashMap<K, V> = HashMap()
    private var current: V? = null

    fun add(key: K, value: V)
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

    fun setCurrentKey(key: K)
    {
        setCurrent(models[key])
    }

    fun setCurrent(`val`: V?)
    {
        if (`val` != null) current = `val`
    }

    fun containsKey(key: K): Boolean
    {
        return models.containsKey(key)
    }

    fun containsValue(value: V): Boolean
    {
        return models.containsValue(value)
    }

    fun current(): V?
    {
        return current
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