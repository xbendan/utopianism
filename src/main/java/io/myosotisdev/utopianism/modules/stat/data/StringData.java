package io.myosotisdev.utopianism.modules.stat.data;

public class StringData
        implements ItemStatData<String>
{
    private String value;

    public StringData() {}

    public StringData(String string)
    {

    }

    @Override
    public String getData()
    {
        return this.value;
    }
}
