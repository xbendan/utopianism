package io.myosotisdev.utopianism.modules.item;

import io.myosotisdev.utopianism.modules.stat.ItemStat;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemMeta;
import net.minestom.server.item.ItemStack;

public class ItemStackBuilder
{
    private ItemTemplate template;

    public ItemStackBuilder(ItemTemplate template)
    {
        this.template = template;
    }

    public ItemStack createItemStacks(int amount)
    {
        ItemStack.Builder builderItemStack = ItemStack.builder(template.getMaterial());
        ItemStack newStack = builderItemStack.amount(amount)
                                             .displayName(Component.text(template.getName()))
                                             .lore(template.getLoreTemplate()
                                                           .createLores(template))
                                             .build()
                                             .withMeta(builder -> builder.customModelData(template.getCustomModelId())
                                                                         .unbreakable(template.isUnbreakable())
                                                                         .damage(template.getDurability())
                                                                         .build());
        template.getItemStats()
                .forEach((itemStat, itemStatData) -> itemStat.onApply(newStack, itemStatData));

        return newStack;
    }
}
