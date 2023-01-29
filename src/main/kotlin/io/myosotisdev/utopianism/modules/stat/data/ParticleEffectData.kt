package io.myosotisdev.utopianism.modules.stat.data

import io.myosotisdev.utopianism.modules.effect.ParticleEffect
import io.myosotisdev.utopianism.serialize.ParticleTagSerializer
import io.myosotisdev.utopianism.serialize.Serializer

class ParticleEffectData : ItemStatData<ParticleEffect>
{
    override var value: ParticleEffect = ParticleEffect()

    companion object
    {
        val Serializer: Serializer<ParticleEffect> = ParticleTagSerializer()
    }
}