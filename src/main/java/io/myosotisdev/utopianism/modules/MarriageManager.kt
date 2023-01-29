package io.myosotisdev.utopianism.modules

import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.marriage.Family
import io.myosotisdev.utopianism.modules.marriage.MarriageData
import io.myosotisdev.utopianism.util.JsonObjects
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.player.PlayerChatEvent
import java.io.File
import java.io.FileReader
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap

class MarriageManager : ModuleManager(Name)
{
    companion object
    {
        val Name: String = "marriage"
    }

    val playerMarriages: HashMap<UUID, MarriageData> = HashMap()
    val familyMapping: HashMap<UUID, Family> = HashMap()

    var familyPvp: Boolean = false

    val consumer_ChatEvent: Consumer<PlayerChatEvent> = Consumer { event ->

    }
    val consumer_AttackEvent: Consumer<EntityAttackEvent> = Consumer { event ->
        val attacker: Entity = event.entity
        val victim: Entity = event.target

        if (attacker is Player && victim is Player)
        {
            if (getFamily(attacker) != null)
            {

            }
        }
    }

    override fun onEnable()
    {

    }

    override fun onDisable()
    {

    }

    fun getFamily(uuid: UUID): Family?
    {
        return familyMapping[uuid]
    }

    fun getFamily(player: Player): Family?
    {
        return null
    }

    fun saveData(player: Player)
    {
        val marriage = playerMarriages[player.uuid]
        if (Objects.nonNull(marriage)) JsonObjects.writeFile(marriage!!.asJsonCopy(),
                File("modules/${name}/${player.uuid}.json"))
    }

    fun loadData(player: Player): MarriageData
    {
        val uuid = player.uuid
        var data: MarriageData? = playerMarriages[uuid]
        if (data != null) return data

        val file = File("modules/${name}/${uuid}.json")
        data = if (file.exists()) MarriageData(JsonParser.parseReader(FileReader(file)).asJsonObject)
        else MarriageData(uuid)
        return data.also { playerMarriages[uuid] = it }
    }

    fun unloadData(player: Player)
    {
        saveData(player)
        playerMarriages.remove(player.uuid)
    }
}