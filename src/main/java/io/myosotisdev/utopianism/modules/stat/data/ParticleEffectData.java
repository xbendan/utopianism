package io.myosotisdev.utopianism.modules.stat.data;

import io.myosotisdev.utopianism.modules.effect.ParticleEffect;
import io.myosotisdev.utopianism.serialize.ParticleTagSerializer;
import io.myosotisdev.utopianism.serialize.Serializer;

public class ParticleEffectData
        implements ItemStatData<ParticleEffect>
{
    public static Serializer<ParticleEffect> Serializer = new ParticleTagSerializer();

    @Override
    public ParticleEffect getData()
    {
        return null;
    }
}
