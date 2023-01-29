package io.myosotisdev.utopianism.modules

import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.model.Economy
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap

class VaultManager : ModuleManager("vault")
{
    val ecos: HashMap<UUID, Economy> = HashMap()

    init
    {
        addListener(AsyncPlayerPreLoginEvent::class.java, Consumer {
            if (!ecos.containsKey(it.playerUuid))
            {
                ecos[it.playerUuid] = Economy()
            }
        })
    }

    override fun onEnable()
    {

    }

    override fun onDisable()
    {

    }

    fun getEconomy(uuid: UUID) = ecos[uuid]

    fun adjust(econ: Economy, operation: String, amount: Double) {
        when(operation)
        {
            "set" -> econ.money = amount
            "give" -> econ.money += amount
            "take" -> econ.money -= amount
            "clear" -> econ.money = 0.0
        }
    }
}