package io.myosotisdev.utopianism.modules

import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.battlepass.BattlePass
import io.myosotisdev.utopianism.modules.battlepass.BattlePassData
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.HashMap

class BattlePassModule : ModuleManager(Name)
{
    val progressMapping: HashMap<UUID, BattlePassData> = HashMap()

    companion object
    {
        val Name: String = "battlepass"
    }

    override fun onEnable()
    {
        BattlePass.Instance = this
    }

    override fun onDisable()
    {

    }

    fun loadData(uuid: UUID): BattlePassData?
    {
        var data = progressMapping[uuid]
        if(data == null)
        {
            val file = File(dataFolder, "$uuid.json")
            if(file.exists())
                data = BattlePassData(JsonParser.parseReader(FileReader(file)).asJsonObject)
        }
        return data
    }
}