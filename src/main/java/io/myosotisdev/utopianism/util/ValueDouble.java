package me.rainbowmeowcat.befall.mathmatic;

import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nullable;

public class ValueDouble extends Number
{
    private String source;
    private double value;
    private BukkitTask taskControl;
    
    public ValueDouble(@Nullable String source, double value)
    {
        this(source, value, null);
    }
    
    public ValueDouble(@Nullable String source, double value, BukkitTask task)
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