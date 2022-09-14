package io.myosotisdev.utopianism.battle;

public class ShieldMeta
{
    private String source;
    private DamageModifier.DamageType type;
    private final int beginValue;
    private int value;
    private boolean breakRemove;

    public ShieldMeta(DamageModifier.DamageType type, int value)
    {
        this.type = type;
        this.beginValue = value;
        this.value = value;
    }

    public DamageModifier.DamageType getType()
    {
        return type;
    }

    public void setType(DamageModifier.DamageType type)
    {
        this.type = type;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public boolean isAcceptable(DamageModifier.DamageType damage)
    {
        return this.type == null || damage == this.type;
    }

    public boolean isBreakRemove()
    {
        return breakRemove;
    }

    public void cancel()
    {
        /**
        if(task != null)
        {
            task.cancel();
        }
         */
    }

    /**
     * @param value
     * @return damage remained
     */
    public int reduce(int value)
    {
        if (value > this.value)
            return value - this.value;
        else
        {
            this.value -= value;
            return 0;
        }
    }
}
