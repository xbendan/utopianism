package io.myosotisdev.utopianism.modules.stat.data;

public class DoubleData
        implements ItemStatData<Double>
{
    private double value;

    @Override
    public Double getData()
    {
        return value;
    }
}
