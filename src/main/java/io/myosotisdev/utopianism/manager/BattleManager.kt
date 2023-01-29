package io.myosotisdev.utopianism.manager

import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.battle.BattleState
import io.myosotisdev.utopianism.battle.BattleStateTagSerializer
import io.myosotisdev.utopianism.modules.entitydata.PlayerData
import io.myosotisdev.utopianism.util.Tags
import net.minestom.server.entity.Player
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent
import java.util.function.Consumer

class BattleManager : ModuleManager("battle")
{
    companion object
    {
        val StateTag = Tags.createTag("battle-state", BattleStateTagSerializer())
    }

    val datas: HashMap<Player, PlayerData> = HashMap()

    override fun onEnable()
    {
        addListener(AsyncPlayerPreLoginEvent::class.java, Consumer {
            it.player.setTag(StateTag, BattleState.NORMAL)
        })
    }

    override fun onDisable()
    {
    }
}