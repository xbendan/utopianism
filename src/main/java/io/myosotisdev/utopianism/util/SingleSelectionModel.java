package io.myosotisdev.utopianism.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SingleSelectionModel<K, V>
{
    private HashMap<K, V> map;
    private V current;

    public SingleSelectionModel()
    {
        this.map = new HashMap<>();
    }

    public HashMap<K, V> getModels()
    {
        return this.map;
    }

    public void add(K key, V value)
    {
        this.map.put(key, value);
    }

    public void addIfAbsent(K key, V value)
    {
        this.map.putIfAbsent(key, value);
    }

    public V get(K key)
    {
        return this.map.get(key);
    }

    public void setCurrentKey(K key)
    {
        setCurrent(this.map.get(key));
    }

    public void setCurrent(V val)
    {
        if(val != null)
            this.current = val;
    }

    public boolean containsKey(K key)
    {
        return map.containsKey(key);
    }

    public boolean containsValue(V value)
    {
        return map.containsValue(value);
    }

    public V current()
    {
        return this.current;
    }

    public Set<K> keys()
    {
        return map.keySet();
    }

    public Collection<V> values()
    {
        return map.values();
    }
}
