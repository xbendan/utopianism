package io.myosotisdev.utopianism.modules.stat.data;

public class BooleanData implements ItemStatData<Boolean>
{
    private boolean value;

    public BooleanData(boolean val)
    {
        this.value = val;
    }

    @Override
    public Boolean getData()
    {
        return value;
    }
}
