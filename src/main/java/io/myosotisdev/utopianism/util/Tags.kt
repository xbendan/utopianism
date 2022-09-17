package io.myosotisdev.utopianism.util

import io.myosotisdev.utopianism.serialize.Serializer
import net.minestom.server.tag.Tag
import net.minestom.server.tag.TagReadable

object Tags
{
    @JvmStatic
    fun createTag(key: String, serializer: Serializer<*>): Tag<*>
    {
        return Tag.Structure(key, serializer.createTagSerializer(key))
    }

    fun compare(key: Tag<*>, readable0: TagReadable, readable1: TagReadable): Boolean
    {
        return readable0.getTag(key) === readable1.getTag(key)
    }

    fun <T> getTagValue(tag: Tag<T>, readable: TagReadable): T
    {
        return readable.getTag(tag)
    }
}