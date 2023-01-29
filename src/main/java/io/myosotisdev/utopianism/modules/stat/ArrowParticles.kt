package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.effect.ParticleEffect
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.data.ParticleEffectData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

class ArrowParticles : ItemStat<ParticleEffect>("arrow-particles",
        Tags.createTag("arrow-particles", ParticleEffectData.Serializer))
{
    override fun onApply(itemStack: ItemStack, data: ItemStatData<out ParticleEffect>)
    {

    }

    override fun defaultStatData(): ItemStatData<ParticleEffect>?
    {
        return ParticleEffectData()
    }

    override fun createStatData(jsonObject: JsonObject): ItemStatData<ParticleEffect>
    {
        TODO("Not yet implemented")
    }
}