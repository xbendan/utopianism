package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.effect.ParticleEffect;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.data.ParticleEffectData;
import io.myosotisdev.utopianism.serialize.ParticleTagSerializer;
import io.myosotisdev.utopianism.serialize.Serializer;
import io.myosotisdev.utopianism.util.Tags;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public class ArrowParticles
        extends ItemStat<ParticleEffect>
{


    ArrowParticles()
    {
        super("arrow-particles", Tags.createTag("arrow-particles", ParticleEffectData.Serializer));


    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {

    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new ParticleEffectData();
    }
}
