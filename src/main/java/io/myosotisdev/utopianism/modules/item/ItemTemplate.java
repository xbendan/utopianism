package io.myosotisdev.utopianism.modules.item;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import net.minestom.server.item.Enchantment;
import net.minestom.server.item.Material;

import java.util.HashMap;

public class ItemTemplate
{
    private String name;
    private Material material;
    private LoreTemplate loreTemplate;
    private int durability;
    private int customModelId;
    private boolean unbreakable;
    private HashMap<Enchantment, Integer> enchants;
    private HashMap<ItemStat, ItemStatData> itemStats;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public LoreTemplate getLoreTemplate()
    {
        return loreTemplate;
    }

    public void setLoreTemplate(LoreTemplate loreTemplate)
    {
        this.loreTemplate = loreTemplate;
    }

    public int getDurability()
    {
        return durability;
    }

    public void setDurability(int durability)
    {
        this.durability = durability;
    }

    public int getCustomModelId()
    {
        return customModelId;
    }

    public void setCustomModelId(int customModelId)
    {
        this.customModelId = customModelId;
    }

    public boolean isUnbreakable()
    {
        return unbreakable;
    }

    public void setUnbreakable(boolean unbreakable)
    {
        this.unbreakable = unbreakable;
    }

    public HashMap<Enchantment, Integer> getEnchants()
    {
        return enchants;
    }

    public HashMap<ItemStat, ItemStatData> getItemStats()
    {
        return itemStats;
    }
}
