package io.myosotisdev.minestom.util

import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance
import java.util.*

class ChunkSnapshot
{
    val chunkX: Int
    val chunkZ: Int
    val worldId: UUID

    constructor(pos: Pos, world: Instance)
    {
        chunkX = pos.chunkX()
        chunkZ = pos.chunkZ()
        worldId = world.uniqueId
    }

    override fun equals(other: Any?): Boolean
    {
        return other is ChunkSnapshot && this.hashCode() == other.hashCode()
    }

    override fun hashCode(): Int
    {
        var hash = 7
        hash = 55 * hash + this.chunkX
        hash = 55 * hash + this.chunkZ
        hash = 55 * hash + this.worldId.hashCode()
        return hash
    }
}