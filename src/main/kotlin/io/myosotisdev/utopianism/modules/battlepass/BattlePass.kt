package io.myosotisdev.utopianism.modules.battlepass

import io.myosotisdev.utopianism.modules.BattlePassModule
import java.util.*

object BattlePass
{
    lateinit var Instance: BattlePassModule

    fun loadData(uuid: UUID): BattlePassData? = Instance.loadData(uuid)
}