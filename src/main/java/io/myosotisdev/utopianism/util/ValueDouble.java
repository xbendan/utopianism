package io.myosotisdev.utopianism.util;

import org.jetbrains.annotations.Nullable;

public class ValueDouble extends Number
{
    private String source;
    private double value;
    
    public ValueDouble(@Nullable String source, double value)
    {
        this.source = source;
        this.value = value;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public void cancel()
    {

    }
    
    public boolean isNeverOutdated()
    {
        return true;
    }
    
    public int getValue()
    {
        return (int)value;
    }
    
    @Override
    public int intValue()
    {
        return (int)value;
    }
    
    @Override
    public long longValue()
    {
        return (long)value;
    }
    
    @Override
    public float floatValue()
    {
        return (float)value;
    }
    
    @Override
    public double doubleValue()
    {
        return value;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof ValueDouble && ((ValueDouble) obj).getValue() == value;
    }
}