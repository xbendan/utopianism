package io.myosotisdev.utopianism.modules.craft

import com.google.common.collect.Sets
import io.myosotisdev.utopianism.modules.CraftingModule

object Crafts
{
    lateinit var Instance: CraftingModule

    fun getRecipes(craftingStationName: String?): Collection<Recipe> = Instance.getRecipes(craftingStationName)
}