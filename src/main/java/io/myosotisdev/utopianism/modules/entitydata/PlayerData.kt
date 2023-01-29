package io.myosotisdev.utopianism.modules.entitydata

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.utopianism.battle.BattleClass
import io.myosotisdev.utopianism.battle.BattleClassData
import io.myosotisdev.utopianism.modules.model.Economy
import io.myosotisdev.utopianism.modules.Exp
import io.myosotisdev.utopianism.modules.homeland.Homeland
import io.myosotisdev.utopianism.modules.team.Team
import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.modules.player.SkillExpert
import io.myosotisdev.utopianism.util.SingleSelectionModel
import net.minestom.server.entity.Player
import java.util.*
import java.util.function.Function

class PlayerData : LivingEntityData,
                   IJsonData
{
    companion object
    {
        val SkillExpertCalc = Function<Int, Int> {
            Math.round(Math.pow(it * 5.0, 2.0) + 10 * it)
                    .toInt()
        }
        val BattleClassCalc = Function<Int, Int> {
            Math.round(Math.pow(it * 10.0, 3.0) * it + 100 * it)
                    .toInt()
        }
    }

    val classModel: SingleSelectionModel<BattleClass, Exp> = SingleSelectionModel()
    val experts: EnumMap<SkillExpert, Exp> = EnumMap(SkillExpert::class.java)
    val player: Player
        get() = entity as Player
    val playerUuid: UUID

    constructor(player: Player) : super(player)
    {
        playerUuid = player.uuid

        SkillExpert.values()
                .forEach { experts[it] = Exp(0.0, SkillExpertCalc) }
        BattleClass.values()
                .forEach { classModel[it] = Exp(0.0, BattleClassCalc) }
    }

    constructor(player: Player, jsonObject: JsonObject) : super(player)
    {
        playerUuid = player.uuid

        val expertsJson = jsonObject["skill-expert"].asJsonObject
        SkillExpert.values()
                .forEach {
                    val itemJobj = expertsJson[it.name.lowercase()]?.asJsonObject
                    if (itemJobj != null) experts[it] = Exp(itemJobj["level"]?.asInt ?: 0,
                            itemJobj["exp"]?.asDouble ?: 0.0,
                            SkillExpertCalc)
                    else experts[it] = Exp(0.0, SkillExpertCalc)
                }

        val classJson = jsonObject["battle-class"].asJsonObject
        BattleClass.values()
                .forEach {
                    val itemJobj = expertsJson[it.name.lowercase()]?.asJsonObject
                    if (itemJobj != null) classModel[it] = Exp(itemJobj["level"]?.asInt ?: 0,
                            itemJobj["exp"]?.asDouble ?: 0.0,
                            BattleClassCalc)
                    else classModel[it] = Exp(0.0, BattleClassCalc)
                }
    }

    fun getExpertLevel(expert: SkillExpert): Int = experts[expert]!!.level

    fun getBattleLevel(bc: BattleClass): Int = classModel[bc]?.level ?: 0

    fun getBattleLevel(): Int = classModel.current?.level ?: 0

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()

        return jsonObject
    }
}