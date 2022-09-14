package io.myosotisdev.utopianism.battle.attributes;

import io.myosotisdev.utopianism.battle.ShieldMeta;
import io.myosotisdev.utopianism.util.IntegerTemplate;
import net.minestom.server.entity.LivingEntity;

import java.util.List;

public class Health
{
    private LivingEntity targetingEntity;
    private IntegerTemplate maxValue;
    private IntegerTemplate regen;
    private int actualHealth;
    private List<ShieldMeta> shields;
}
