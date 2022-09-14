package io.myosotisdev.utopianism.util;

import org.jetbrains.annotations.Nullable;

public class ValueInt extends Number
{
    private String source;
    private int value;
    
    public ValueInt(@Nullable String source, int value)
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
        return true;//taskControl == null;
    }
    
    public int getValue()
    {
        return value;
    }
    
    @Override
    public int intValue()
    {
        return (int)value;
    }
    
    @Override
    public long longValue()
    {
        return value;
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
        return obj instanceof ValueInt && ((ValueInt) obj).getValue() == value;
    }
}
