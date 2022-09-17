package io.myosotisdev.utopianism.modules.dungeon

import io.myosotisdev.utopianism.modules.player.MsPlayer
import io.myosotisdev.utopianism.modules.region.Region
import net.minestom.server.instance.Instance

/**
 *
 */
class Dungeon(val record: DungeonRecord, val region: Region, val world: Instance)
{
    val players: List<MsPlayer> = ArrayList()

    @Volatile
    var countdown: Long = 0
}