package io.myosotisdev.utopianism.modules.guild

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.modules.Economy
import io.myosotisdev.utopianism.modules.region.IRegionKeeper
import io.myosotisdev.utopianism.modules.region.Region
import net.minestom.server.entity.Player
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Guild : IRegionKeeper
{
    var name: String? = null;
    var manifest: MutableList<String>? = null
    var inventoryIconTextures: String? = null
    var leader: Player
    val players: HashMap<Player, GuildClass> = HashMap()
    override val regions: HashSet<Region> = HashSet()

    override fun acquireRegion(region: Region?)
    {
        TODO("Not yet implemented")
    }

    override fun releaseRegion(region: Region?)
    {
        TODO("Not yet implemented")
    }

    val uid: UUID
    var level = 0
    val bank: Economy = Economy()

    constructor(uid: UUID, name: String, player: Player)
    {
        this.uid = uid;
        this.name = name;
        this.leader = player
    }

    constructor(uid: UUID, gFile: File)
    {
        this.uid = uid;

        var gObject: JsonObject = JsonParser.parseReader(FileReader(gFile)).asJsonObject
        var jManifest = gObject["manifest"].asJsonArray
        var jPlayers = gObject["players"].asJsonArray
        var jRegions = gObject["regions"].asJsonArray

        this.name = gObject["name"].asString;
        this.manifest = MutableList(jManifest.size()) { index -> jManifest.get(index).asString }
        this.leader = Minestom.getPlayer(UUID.fromString(gObject["leader"].asString))!!
        this.level = gObject["level"].asInt
        this.bank.money = gObject["bank"].asDouble

        jPlayers.forEach { element ->
            var pObj = element.asJsonObject
            players.put(Minestom.getPlayer(UUID.fromString(pObj["uuid"].asString))!!, GuildClass.valueOf(pObj["class"].asString.uppercase()))
        }

        jRegions.forEach { element ->

        }
    }

    fun upgrade(): Boolean
    {
        return false
    }

    fun dissolve()
    {

    }
}