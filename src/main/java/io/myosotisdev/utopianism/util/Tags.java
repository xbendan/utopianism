package io.myosotisdev.utopianism.util;

import io.myosotisdev.utopianism.serialize.Serializer;
import net.minestom.server.tag.Tag;
import net.minestom.server.tag.TagReadable;

public class Tags
{
    public static Tag createTag(String key, Serializer serializer)
    {
        return Tag.Structure(key, serializer.createTagSerializer(key));
    }

    public static boolean compare(Tag key, TagReadable readable0, TagReadable readable1)
    {
        return readable0.getTag(key) == readable1.getTag(key);
    }

    public static <T> T getTagValue(Tag<T> tag, TagReadable readable)
    {
        return readable.getTag(tag);
    }
}
