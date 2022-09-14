package io.myosotisdev.utopianism.battle;

import io.myosotisdev.utopianism.util.IntegerTemplate;

public class DamageModifier
{
    private String source;
    private DamageType type;
    private IntegerTemplate value;
    private boolean ignoreShield;

    public DamageModifier(DamageType type, int value)
    {
        this(type, value, false);
    }

    public DamageModifier(DamageType type, int value, boolean ignoreShield)
    {
        this.type = type;
        this.value = new IntegerTemplate(value);
        this.ignoreShield = ignoreShield;
    }

    public DamageType getType()
    {
        return type;
    }

    public IntegerTemplate getValueTemplate()
    {
        return value;
    }

    public int getValue()
    {
        return value.getValue();
    }

    public boolean ignoreShield()
    {
        return ignoreShield;
    }

    public static enum DamageType
    {
        PHYSIC,
        MAGIC,
        TRUE;
    }
}
