package io.myosotisdev.utopianism.modules.stat

import io.myosotisdev.utopianism.modules.effect.ParticleEffect
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.data.ParticleEffectData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class ArrowParticles() : ItemStat<ParticleEffect>("arrow-particles", Tags.createTag("arrow-particles", ParticleEffectData.Serializer) as Tag<ParticleEffect>)
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<in ParticleEffect>)
    {

    }

    override fun defaultStatData(): ItemStatData<ParticleEffect>?
    {
        return ParticleEffectData()
    }
}