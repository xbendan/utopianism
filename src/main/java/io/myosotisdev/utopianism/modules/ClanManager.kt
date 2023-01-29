package io.myosotisdev.utopianism.modules

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.clan.Clan
import io.myosotisdev.utopianism.modules.clan.ClanVars
import io.myosotisdev.utopianism.modules.player.money
import io.myosotisdev.utopianism.util.JsonObjects
import net.minestom.server.entity.Player
import java.io.FileReader
import java.io.IOException
import java.util.*

class ClanManager : ModuleManager("clan")
{
    val clans: HashMap<UUID, Clan> = HashMap()
    val clanMembers: HashMap<UUID, Clan> = HashMap()

    override fun onEnable()
    {
        val regexExact = Regex("\\w{8}(-\\w{4}){3}-\\w{12}.json")
        val regexShort = Regex("\\w{32}.json")
        dataFolder.listFiles()
                ?.forEach {
                    val name = it.name
                    if (regexExact.matches(name) || regexShort.matches(name))
                    {
                        val uuid = UUID.fromString(name.replace(".json", "")
                                .replace("-", ""))
                        try
                        {
                            val json = JsonParser.parseReader(FileReader(it)).asJsonObject
                            clans[uuid] = Clan(uuid, json).also { clan ->
                                clan.members.forEach { clanMembers[it.key] = clan }
                            }
                        }
                        catch (_: IOException)
                        {
                        }
                    }
                }
    }

    override fun onDisable()
    {
    }

    fun create(name: String, player: Player): Clan
    {
        player.money -= ClanVars.CreationCost

        val clan = Clan(UUID.randomUUID(), name, player)
        clans[clan.uid] = clan
        return clan
    }

    fun remove(clan: Clan)
    {
        clans.remove(clan.uid)
    }

    fun leave(player: Player)
    {
        val clan = findByPlayer(player.uuid)

        if (Objects.isNull(clan))
        {
            player.sendMessage("&c你不属于任何公会")
            return
        }

        if (clan!!.isMaster(player))
        {
            player.sendMessage("&c公会会长只能解散公会，或者转让后再退出")
            return
        }


    }

    fun loadClan(uuid: UUID): Clan?
    {
        var data: Clan? = clans[uuid]
        if (data == null)
        {
            val jsonObject: JsonObject? = JsonObjects.readFile("modules/${name}/$uuid")?.asJsonObject
            if (jsonObject != null) data = Clan(uuid, jsonObject).also { clans[uuid] = it }
        }

        return data
    }

    fun findByPlayer(uuid: UUID): Clan? = clanMembers[uuid]

    fun findByName(name: String): Clan? = clans.values.stream()
            .filter { g -> g.name == name }
            .findFirst()
            .orElse(null)

    fun findByUUID(uuid: UUID): Clan? = clans[uuid]

    fun all(): Collection<Clan> = clans.values
}