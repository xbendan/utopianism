package io.myosotisdev.utopianism.serialize

import io.myosotisdev.utopianism.modules.effect.ParticleEffect

class ParticleTagSerializer : Serializer<ParticleEffect?>()
{
    override fun serialize(obj: ParticleEffect?): String?
    {
        return null
    }

    override fun deserialize(string: String?): ParticleEffect?
    {
        val args = string!!.split(",".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
        return ParticleEffect()
    }
}