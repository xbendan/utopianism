package io.myosotisdev.utopianism.battle

import io.myosotisdev.utopianism.modules.player.Moving
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
import net.minestom.server.event.player.PlayerSwapItemEvent
import java.util.function.Consumer

enum class BattleState(
        val text: String,
        val blockBreakEvent: Consumer<PlayerBlockBreakEvent>,
        val blockPlaceEvent: Consumer<PlayerBlockPlaceEvent>,
        val attackOnEntityEvent: Consumer<EntityDamageEvent>,
        val startSpiritingEvent: Consumer<PlayerStartSprintingEvent>,
        val stopSprintingEvent: Consumer<PlayerStopSprintingEvent>,
        val startSneakingEvent: Consumer<PlayerStartSneakingEvent>,
        val stopSneakingEvent: Consumer<PlayerStopSneakingEvent>,
        val deathEvent: Consumer<PlayerDeathEvent>,
        val respawnEvent: Consumer<PlayerRespawnEvent>,
        val interactEvent: Consumer<PlayerEntityInteractEvent>
)
{
    /* When entered dungeons and wars */
    COMBAT("&c战斗中",
            { it.isCancelled = true }, { it.isCancelled = true },
            {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When stay at homeland */
    HOMELAND("&a家园中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* Normal state */
    NORMAL("&a在线", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When stay at homeland and modifying buildings */
    BUILDING("&a建造中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }),
    /* When out of fortress */
    EXPLORING("&a探索中", {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  }, {  })
}