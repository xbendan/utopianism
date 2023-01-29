package io.myosotisdev.utopianism.battle

import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.event.player.PlayerDeathEvent
import net.minestom.server.event.player.PlayerEntityInteractEvent
import net.minestom.server.event.player.PlayerRespawnEvent
import net.minestom.server.event.player.PlayerStartSneakingEvent
import net.minestom.server.event.player.PlayerStartSprintingEvent
import net.minestom.server.event.player.PlayerStopSneakingEvent
import net.minestom.server.event.player.PlayerStopSprintingEvent
import java.util.function.Consumer

enum class BattleState(
        val text: String,
        blockBreakEvent: Consumer<PlayerBlockBreakEvent>,
        blockPlaceEvent: Consumer<PlayerBlockPlaceEvent>,
        attackOnEntityEvent: Consumer<EntityDamageEvent>,
        startSpiritingEvent: Consumer<PlayerStartSprintingEvent>,
        stopSprintingEvent: Consumer<PlayerStopSprintingEvent>,
        startSneakingEvent: Consumer<PlayerStartSneakingEvent>,
        stopSneakingEvent: Consumer<PlayerStopSneakingEvent>,
        deathEvent: Consumer<PlayerDeathEvent>,
        respawnEvent: Consumer<PlayerRespawnEvent>,
        interactEvent: Consumer<PlayerEntityInteractEvent>
)
{
    /* When entered dungeons and wars */
    COMBAT("&c战斗中",
            { it.isCancelled = true }, { it.isCancelled = true },
            {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When stay at homeland */
    HOMELAND("&a家园中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* Normal state */
    NORMAL("&a在线",
            {

            }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When stay at homeland and modifying buildings */
    BUILDING("&a建造中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When out of fortress */
    EXPLORING("&a探索中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  })
}