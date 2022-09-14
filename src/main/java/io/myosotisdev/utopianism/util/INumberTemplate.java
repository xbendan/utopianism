package io.myosotisdev.utopianism.util;

public interface INumberTemplate<T extends Number>
{
    public T requestValue();

    public default void addProportion(String key, double percentage)
    {
        addProportion(key, percentage, -1);
    }

    public void addProportion(String key, double percentage, int duration);

    public default void addValue(String key, int value)
    {
        addValue(key, value, -1);
    }

    public void addValue(String key, int value, int duration);

    public T getValue();
}
