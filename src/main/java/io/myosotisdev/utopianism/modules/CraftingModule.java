package io.myosotisdev.utopianism.modules;

import com.google.common.collect.Sets;
import io.myosotisdev.minestom.module.Module;
import io.myosotisdev.utopianism.Ut;
import io.myosotisdev.utopianism.modules.craft.Recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class CraftingModule extends Module
{
    private final HashMap<String, Set<Recipe>> commonRecipes;

    public CraftingModule()
    {
        super(Ut.ModuleCraft);
        this.commonRecipes = new HashMap<>();
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }

    public Collection<Recipe> getRecipes(String craftingStationName)
    {
        return commonRecipes.getOrDefault(craftingStationName, Sets.newHashSet());
    }
}
