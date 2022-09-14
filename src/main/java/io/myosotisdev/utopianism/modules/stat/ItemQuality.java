package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.modules.item.Quality;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.data.QualityData;
import io.myosotisdev.utopianism.serialize.QualityTagSerializer;
import io.myosotisdev.utopianism.serialize.Serializer;
import io.myosotisdev.utopianism.util.Tags;
import net.minestom.server.item.ItemStack;

public class ItemQuality
    extends ItemStat
{
    static final Serializer<Quality> Serializer = new QualityTagSerializer();

    public ItemQuality()
    {
        super("quality", Tags.createTag("quality", Serializer));
    }

    @Override
    public void onApply(ItemStack itemStack, ItemStatData data)
    {
        return;
    }

    @Override
    public ItemStatData defaultStatData()
    {
        return new QualityData();
    }
}
