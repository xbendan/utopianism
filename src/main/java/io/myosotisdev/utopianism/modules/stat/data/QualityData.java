package io.myosotisdev.utopianism.modules.stat.data;

import io.myosotisdev.utopianism.modules.item.Quality;

public class QualityData
        implements ItemStatData<Quality>
{
    private Quality quality;

    public QualityData()
    {
        this(Quality.NORMAL);
    }

    public QualityData(Quality quality)
    {
        this.quality = quality;
    }

    @Override
    public Quality getData()
    {
        return null;
    }
}
