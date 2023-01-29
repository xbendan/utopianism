package io.myosotisdev.utopianism.listeners

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.battle.BattleState
import io.myosotisdev.utopianism.manager.BattleManager
import io.myosotisdev.utopianism.modules.task.ItemReward
import io.myosotisdev.utopianism.modules.task.Reward
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.item.Material
import java.io.File
import java.io.FileReader
import java.util.function.Consumer

class BlockEvents
{
    val blockResourceMapping: HashMap<Material, Array<Reward>> = HashMap()
    val blockDropMapping: HashMap<Material, Array<ItemReward>> = HashMap()

    val blockMiningHandler: Consumer<PlayerBlockBreakEvent> = Consumer { event ->

    }

    init
    {
        var blocksFile: File = File("modules/blocks.json")
        if (blocksFile.exists())
        {
            var json: JsonObject = JsonParser.parseReader(FileReader(blocksFile)).asJsonObject
            json.entrySet()
                    .forEach { entry ->
                        val material: Material = Material.fromNamespaceId(entry.key) ?: Material.AIR
                        val jsonEntry: JsonObject = entry.value.asJsonObject

                        var valueArray: JsonArray = jsonEntry["rewards"].asJsonArray
                        blockResourceMapping.putIfAbsent(material, Array(valueArray.size()) { i -> Reward.deserialize(valueArray[i].asString)!! })

                        var dropArray: JsonArray = jsonEntry["drops"].asJsonArray
                        blockDropMapping.putIfAbsent(material, Array(dropArray.size()) { i -> Reward.deserialize(dropArray[i].asString) as ItemReward })
                    }
        }

        Minestom.registerListener(PlayerBlockBreakEvent::class.java) { event ->
            val player = event.player
            when(player.getTag(BattleManager.StateTag)!!)
            {
                /**
                 *
                 */
                BattleState.COMBAT ->
                {

                }
                /**
                 * 检查模板
                 */
                BattleState.HOMELAND ->
                {

                }
                /**
                 * 检查区域 region 是否允许挖掘
                 * 处理挖掘掉落
                 */
                BattleState.NORMAL   ->
                {

                }
                BattleState.BUILDING ->
                {

                }
                BattleState.EXPLORING ->
                {

                }
            }
        }
    }
}
   