package me.rainbowmeowcat.befall.mathmatic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class IntegerTemplate implements INumberTemplate<Integer>
{
    private int base;
    private final boolean useProportion;
    private final boolean useValues;
    private Map<String, ValueDouble> proportion;
    private Map<String, ValueInt> values;

    private int latest;

    public IntegerTemplate(int base)
    {
        this(base, true, true);
    }

    public IntegerTemplate(int base, boolean useProportion, boolean useValues)
    {
        this.base = base;
        this.useProportion = useProportion;
        this.useValues = useValues;
        this.proportion = new HashMap<>();
        this.values = new HashMap<>();
    }

    @Override
    public void addProportion(String key, double percentage, int duration)
    {

    }

    @Override
    public void addValue(String key, int value, int duration)
    {

    }

    @Override
    public Integer requestValue()
    {
        latest = base
                + (useProportion ? new BigDecimal(base * (proportion.values().stream().mapToDouble(value -> value.doubleValue()).reduce(0, Double::sum))).intValue() : 0)
                + (useValues ? values.values().stream().mapToInt(value -> value.intValue()).reduce(0, Integer::sum) : 0);
        return getValue();
    }

    @Override
    public Integer getValue()
    {
        return latest;
    }
}
