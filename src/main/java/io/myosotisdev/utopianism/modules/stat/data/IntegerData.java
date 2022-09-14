package io.myosotisdev.utopianism.modules.stat.data;

public class IntegerData implements ItemStatData<Integer>
{
    private int value;

    @Override
    public Integer getData()
    {
        return this.value;
    }
}
