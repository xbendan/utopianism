package me.rainbowmeowcat.befall.mathmatic;

import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nullable;

public class ValueInt extends Number
{
    private String source;
    private int value;
    private BukkitTask taskControl;
    
    public ValueInt(@Nullable String source, int value)
    {
        this(source, value, null);
    }
    
    public ValueInt(@Nullable String source, int value, BukkitTask task)
    {
        this.source = source;
        this.value = value;
        this.taskControl = task;
    }
    
    public String getSource()
    {
        return source;
    }
    
    public BukkitTask getTaskControl()
    {
        return taskControl;
    }
    
    public void cancel()
    {
        if(taskControl != null)
        {
            taskControl.cancel();
        }
    }
    
    public boolean isNeverOutdated()
    {
        return taskControl == null;
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
