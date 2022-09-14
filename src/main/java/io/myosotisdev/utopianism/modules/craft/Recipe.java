package io.myosotisdev.utopianism.modules.craft;

import io.myosotisdev.utopianism.modules.Experience;
import io.myosotisdev.utopianism.modules.expert.SkillExpert;
import io.myosotisdev.utopianism.modules.player.ImplPlayer;
import io.myosotisdev.utopianism.util.Items;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;

import java.util.List;
import java.util.Map;

public class Recipe
{
    private String stationName;
    private int duration;
    private Map<Integer, ItemStack> ingredients;
    private boolean sorted;
    private List<ItemStack> outputs;
    private Map<SkillExpert, Integer> requiredExpertsLevel;

    public Recipe(String stationName, int duration, Map<Integer, ItemStack> ingredients, boolean sorted, List<ItemStack> outputs, Map<SkillExpert, Integer> requiredExpertsLevel)
    {
        this.stationName = stationName;
        this.duration = duration;
        this.ingredients = ingredients;
        this.sorted = sorted;
        this.outputs = outputs;
        this.requiredExpertsLevel = requiredExpertsLevel;
    }

    public String getStationName()
    {
        return stationName;
    }

    public int getDuration()
    {
        return duration;
    }

    public Map<Integer, ItemStack> getIngredients()
    {
        return ingredients;
    }

    public boolean isSorted()
    {
        return sorted;
    }

    public List<ItemStack> getOutputs()
    {
        return outputs;
    }

    public Map<SkillExpert, Integer> getRequiredLevels()
    {
        return requiredExpertsLevel;
    }

    public boolean checkPlayer(Player player)
    {

        return requiredExpertsLevel.isEmpty() || requiredExpertsLevel.entrySet()
                                                                     .stream()
                                                                     .allMatch(entry -> ((ImplPlayer) player).getExpertExperience(entry.getKey())
                                                                                                             .getLevel() >= entry.getValue());
    }

    public boolean checkIngredients(Map<Integer, ItemStack> map)
    {
        return ingredients.entrySet()
                          .stream()
                          .allMatch(entry -> {
                              ItemStack hasItem = map.getOrDefault(entry.getKey(), null);
                              if (hasItem == null)
                                  return false;
                              else {
                                  ItemStack requiredItem = entry.getValue();
                                  return Items.compare(hasItem, requiredItem) && hasItem.amount() >= requiredItem.amount();
                              }
                          });
    }


}
