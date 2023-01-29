package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.minestom.util.ChunkSnapshot
import io.myosotisdev.utopianism.modules.region.Region
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap

class RegionModule : ModuleManager(Name)
{
    companion object { val Name: String = "region" }

    val chunksMapping: ConcurrentHashMap<ChunkSnapshot, MutableSet<UUID>> = ConcurrentHashMap()
    val areaIdMapping: HashMap<UUID, Region> = HashMap()

    override fun onEnable()
    {
        TODO("Not yet implemented")
    }

    override fun onDisable()
    {
        TODO("Not yet implemented")
    }
}