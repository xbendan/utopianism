package io.myosotisdev.utopianism.modules.player

import io.myosotisdev.utopianism.modules.party.Party
import net.minestom.server.entity.Player

class MsPlayerExt

fun Player.getParty(): Party?
{
    return (this as MsPlayer).party
}

