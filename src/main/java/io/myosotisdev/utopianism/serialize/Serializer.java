package io.myosotisdev.utopianism.serialize;

import net.minestom.server.tag.TagSerializer;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;
import org.jglrxavpok.hephaistos.nbt.NBTString;

import java.util.HashMap;
import java.util.function.Function;

public abstract class Serializer<T>
{
    public TagSerializer<T> createTagSerializer(String key)
    {
        return TagSerializer.fromCompound(
                compound -> this.deserialize(compound.getString(key)),
                particle -> new NBTCompound().withEntries(NBT.Entry(key, new NBTString(this.serialize(particle))))
        );
    }

    public abstract String serialize(T obj);

    public abstract T deserialize(String string);
}
