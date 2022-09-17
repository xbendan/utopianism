package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.Module
import io.myosotisdev.utopianism.Ut
import io.myosotisdev.utopianism.modules.party.Party
import io.myosotisdev.utopianism.modules.player.getParty
import net.minestom.server.entity.Player
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class PartyModule : Module(Ut.ModuleParty)
{
    private val parties: HashSet<Party> = HashSet()
    val applyCodes: ArrayList<String> = ArrayList()

    override fun onEnable()
    {

    }

    override fun onDisable()
    {
    }

    fun createParty(name: String?, player: Player): Party?
    {
        if (player.getParty() != null)
        {
            player.sendMessage("You have already joined into a team.")
            return null
        }

        var party = Party(UUID.randomUUID(), name, player);
        parties.add(party);

        return party
    }

    fun createParty(player: Player): Party?
    {
        return createParty(null, player)
    }
}