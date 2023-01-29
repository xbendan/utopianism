package io.myosotisdev.utopianism.modules

import com.google.common.collect.Sets
import io.myosotisdev.minestom.module.ModuleManager
import io.myosotisdev.utopianism.modules.craft.Crafts
import io.myosotisdev.utopianism.modules.craft.Recipe

class CraftingModule : ModuleManager(Name)
{
    companion object { val Name: String = "craft" }

    private val commonRecipes: HashMap<String, Set<Recipe>> = HashMap()

    init
    {
        Crafts.Instance = this
    }

    override fun onEnable()
    {
    }

    override fun onDisable()
    {
    }

    fun getRecipes(craftingStationName: String?): Collection<Recipe>
    {
        return commonRecipes.getOrDefault(craftingStationName, Sets.newHashSet())
    }
}