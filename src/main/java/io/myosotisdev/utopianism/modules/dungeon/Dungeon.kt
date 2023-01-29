package io.myosotisdev.utopianism.modules.dungeon

import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.modules.region.Region
import net.minestom.server.instance.Instance

/**
 *
 */
class Dungeon(val record: DungeonRecord, val region: Region, val world: Instance)
{
    val players: List<PlayerEx> = ArrayList()

    @Volatile
    var countdown: Long = 0
}