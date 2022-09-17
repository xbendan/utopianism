package io.myosotisdev.utopianism.modules.craft

import io.myosotisdev.utopianism.modules.expert.SkillExpert
import io.myosotisdev.utopianism.modules.player.MsPlayer
import io.myosotisdev.utopianism.util.Items
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

class Recipe(val stationName: String, val duration: Int, val ingredients: Map<Int, ItemStack>, val isSorted: Boolean, val outputs: List<ItemStack>, val requiredLevels: Map<SkillExpert, Int>)
{

    fun checkPlayer(player: Player): Boolean
    {
        return requiredLevels.isEmpty() || requiredLevels.entries
                .stream()
                .allMatch { (key, value): Map.Entry<SkillExpert, Int> ->
                    (player as MsPlayer).getExpertExperience(key)
                             .level >= value
                }
    }

    fun checkIngredients(map: Map<Int?, ItemStack?>): Boolean
    {
        return ingredients.entries
                .stream()
                .allMatch { (key, requiredItem): Map.Entry<Int, ItemStack> ->
                    val hasItem = map.getOrDefault(key, null)
                    if (hasItem == null) return@allMatch false
                    else
                    {
                        return@allMatch Items.compare(hasItem, requiredItem) && hasItem.amount() >= requiredItem.amount()
                    }
                }
    }
}