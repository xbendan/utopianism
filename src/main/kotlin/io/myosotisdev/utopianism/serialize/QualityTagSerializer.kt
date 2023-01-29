package io.myosotisdev.utopianism.serialize

import io.myosotisdev.utopianism.modules.item.Quality

class QualityTagSerializer : Serializer<Quality>()
{
    override fun serialize(obj: Quality?): String?
    {
        return null
    }

    override fun deserialize(string: String?): Quality?
    {
        return null
    }
}