package io.myosotisdev.utopianism.serialize

import net.minestom.server.tag.TagSerializer
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTString

abstract class Serializer<T>
{
    fun createTagSerializer(key: String?): TagSerializer<T>
    {
        return TagSerializer.fromCompound(
                { compound: NBTCompound -> deserialize(compound.getString(key!!)) }
        ) { particle: T -> NBTCompound().withEntries(NBT.Entry(key!!, NBTString(serialize(particle)!!))) }
    }

    abstract fun serialize(obj: T?): String?
    abstract fun deserialize(string: String?): T?
}