package io.myosotisdev.utopianism.ui.generic

import net.minestom.server.entity.Player

class VisualTeam(val player: Player) : VisualInventory("队伍信息")
{
    init
    {
        dividers(SlotAround, DividerBlackPanel)
    }

    override fun slots(vararg slots: Int)
    {

    }
}