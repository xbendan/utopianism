package io.myosotisdev.utopianism.modules.player

import io.myosotisdev.minestom.entity.PlayerEx
import io.myosotisdev.utopianism.battle.attributes.Health
import io.myosotisdev.utopianism.entity.PlayerData
import io.myosotisdev.utopianism.modules.Economy
import io.myosotisdev.utopianism.modules.Experience
import io.myosotisdev.utopianism.modules.expert.SkillExpert
import io.myosotisdev.utopianism.modules.guild.Guild
import io.myosotisdev.utopianism.modules.guild.IGuildMember
import io.myosotisdev.utopianism.modules.party.IPartyMember
import io.myosotisdev.utopianism.modules.party.Party
import io.myosotisdev.utopianism.modules.task.ITaskExecutor
import net.minestom.server.network.player.PlayerConnection
import java.util.*

class MsPlayer(uuid: UUID, username: String, playerConnection: PlayerConnection) : PlayerEx(uuid, username, playerConnection),
                                                                                   IPartyMember,
                                                                                   ITaskExecutor,
                                                                                   IGuildMember
{
    override var guild: Guild? = null
        set(value)
        {
        }
    val data: PlayerData = PlayerData(this)
    val economy: Economy = Economy()
    override var party: Party? = null

    private val locale: String? = null
    val healthBar: Health
        get() = data.health

    override fun getHealth(): Float
    {
        return super.getHealth()
    }

    fun getExpertExperience(skillExpert: SkillExpert): Experience
    {
        return data.experts[skillExpert]!!
    }

    fun getExpertLevel(expert: SkillExpert): Int
    {
        return data.getExpertLevel(expert)
    }

    val money: Double
        get() = economy!!.money

    override fun isPartyLeader(): Boolean
    {
        return party?.getLeader() == this
    }

    override fun leaveParty()
    {
    }
}