package io.myosotisdev.utopianism.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DoubleTemplate implements INumberTemplate<Double>
{
    private double base;
    private final boolean useProportion;
    private final boolean useValues;
    private Map<String, ValueDouble> proportion;
    private Map<String, ValueDouble> values;
    private Runnable onValueChanged;

    private double latest = -1;

    public DoubleTemplate(double base)
    {
        this(base, true, true);
    }

    public DoubleTemplate(double base, Runnable onValueChanged)
    {
        this(base, true, true);
        this.onValueChanged = onValueChanged;
    }

    public DoubleTemplate(double base, boolean useProportion, boolean useValues)
    {
        this.base = base;
        this.useProportion = useProportion;
        this.useValues = useValues;
        this.proportion = new HashMap<>();
        this.values = new HashMap<>();
    }

    public void setOnValueChanged(Runnable runnable)
    {
        this.onValueChanged = runnable;
    }

    @Override
    public Double requestValue()
    {
        latest = base
                + (useProportion ? new BigDecimal(base * (proportion.values().stream().mapToDouble(value -> value.doubleValue()).reduce(0, Double::sum))).intValue() : 0)
                + (useValues ? values.values().stream().mapToDouble(value -> value.doubleValue()).reduce(0, Double::sum) : 0);
        if (onValueChanged != null)
        {
            onValueChanged.run();
        }
        return getValue();
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
    public Double getValue()
    {
        return latest;
    }
}
