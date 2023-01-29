package io.myosotisdev.utopianism.modules

import com.google.gson.JsonObject
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.minestom.util.Files
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.modules.homeland.Homeland
import io.myosotisdev.utopianism.util.JsonObjects
import net.minestom.server.entity.Player
import java.io.File
import java.util.UUID

class HomelandManager : ModuleManager(Name)
{
    companion object { val Name: String = "homeland" }

    val landMapping: HashMap<UUID, Homeland> = HashMap()

    override fun onEnable()
    {

    }

    override fun onDisable()
    {

    }

    fun createLand(player: Player, templateName: String?): Homeland?
    {
        return createLand(player, Minestom.world().templates[templateName])
    }

    fun createLand(player: Player, template: File?): Homeland?
    {
        if (landMapping[player.uuid] != null)
        {
            player.sendMessage(Components.fromLegacy("You have already owned a homeland"))
            return null
        }

        var homeland: Homeland = Homeland(player)

        homeland.lock = true
        Minestom.runTaskAsync {
            if (template != null)
            {
                Files.copyDirectory(template, File("modules/homeland/${player.uuid}"))

            }
            else
            {

            }
        }

        return homeland
    }

    fun restore(player: Player)
    {

    }

    fun loadLand(uuid: UUID): Homeland?
    {
        var data: Homeland? = landMapping[uuid]
        if (data == null)
        {
            val jsonObject: JsonObject? = JsonObjects.readFile("modules/${name}/$uuid")?.asJsonObject
            if (jsonObject != null) data = Homeland(uuid, jsonObject).also { landMapping[uuid] = it }
        }

        return data
    }
}