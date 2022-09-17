package io.myosotisdev.utopianism.modules

import com.google.common.collect.Sets
import io.myosotisdev.minestom.module.Module
import io.myosotisdev.utopianism.Ut
import io.myosotisdev.utopianism.modules.craft.Recipe

class CraftingModule : Module(Ut.ModuleCraft)
{
    private val commonRecipes: HashMap<String, Set<Recipe>> = HashMap()

    init
    {
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