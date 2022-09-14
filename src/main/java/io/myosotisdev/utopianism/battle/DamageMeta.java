package io.myosotisdev.utopianism.battle;

import io.myosotisdev.utopianism.Ut;
import io.myosotisdev.utopianism.entity.LivingEntityData;
import io.myosotisdev.utopianism.util.IntegerTemplate;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class DamageMeta
{
    private       LivingEntityData                                attacker;
    private       LivingEntityData                                victim;
    private       ItemStack                                       weapon;
    private final Map<String, DamageModifier>                     damageModifiers;
    private final Map<String, DefenseModifier>                    defenseModifiers;
    private       Map<DamageModifier.DamageType, IntegerTemplate> latestDamage;
    private       boolean                                         isProjectileDamage;
    private       boolean                                         isSpellDamage;
    private       boolean                                         isTrapDamage;

    DamageMeta()
    {
        this.damageModifiers = new HashMap<>();
        this.defenseModifiers = new HashMap<>();
    }

    public DamageMeta(LivingEntityData attacker, LivingEntityData victim)
    {
        this();
    }

    public DamageMeta(LivingEntity attacker, LivingEntity victim)
    {
        this(attacker != null ?
                        (LivingEntityData) Ut.entitydata()
                                             .getData(attacker) :
                        null,
                victim != null ?
                        (LivingEntityData) Ut.entitydata()
                                             .getData(victim) :
                        null);
    }

    public LivingEntityData getAttacker()
    {
        return attacker;
    }

    public LivingEntityData getVictim()
    {
        return victim;
    }

    public Map<String, DamageModifier> getDamageModifiers()
    {
        return damageModifiers;
    }

    public Map<String, DefenseModifier> getDefenseModifiers()
    {
        return defenseModifiers;
    }

    public Map<DamageModifier.DamageType, IntegerTemplate> getDamageMap()
    {
        return latestDamage;
    }

    public double getLatestDamage()
    {
        return 0.0d;
    }

    public boolean isProjectileDamage()
    {
        return isProjectileDamage;
    }

    public void setProjectileDamage(boolean projectileDamage)
    {
        isProjectileDamage = projectileDamage;
    }

    public boolean isSpellDamage()
    {
        return isSpellDamage;
    }

    public void setSpellDamage(boolean spellDamage)
    {
        isSpellDamage = spellDamage;
    }

    public boolean isTrapDamage()
    {
        return isTrapDamage;
    }

    public void setTrapDamage(boolean trapDamage)
    {
        isTrapDamage = trapDamage;
    }
}
