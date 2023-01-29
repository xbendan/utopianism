package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.minestom.util.ChunkSnapshot
import io.myosotisdev.utopianism.modules.teleport.AnchorPoint
import io.myosotisdev.utopianism.modules.teleport.ITeleportable

class TeleportModule : ModuleManager(Name)
{
    companion object { val Name: String = "teleport" }

    val anchorPoints: HashMap<ChunkSnapshot, Set<AnchorPoint>> = HashMap()

    override fun onEnable()
    {
        TODO("Not yet implemented")
    }

    override fun onDisable()
    {
        TODO("Not yet implemented")
    }

    fun checkCooldown(who: ITeleportable, anchorPoint: AnchorPoint)
    {

    }
}