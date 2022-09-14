package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.data.ParticleEffectData;
import io.myosotisdev.utopianism.util.Tags;
import net.minestom.server.item.ItemStack;

public class HoldingParticles extends ItemStat
{
    public HoldingParticles()
    {
        super("holding-particles", Tags.createTag("holding-particles", ParticleEffectData.Serializer));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new ParticleEffectData();
    }
}
