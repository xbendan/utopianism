package io.myosotisdev.utopianism.util

import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.modules.stat.ItemStat
import net.minestom.server.entity.PlayerSkin
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.PlayerHeadMeta
import java.util.UUID
import java.util.function.Consumer

object ItemStacks
{
    fun isSimilar(stack0: ItemStack, stack1: ItemStack): Boolean
    {
        return (Tags.compare(ItemStat.ITEM_ID.tag, stack0, stack1) && Tags.compare(ItemStat.REINFORCE.tag,
                stack0,
                stack1))
    }

    fun new(material: Material, displayName: String, lores: List<String> = listOf(), customModelData: Int = 0, vararg hideFlag: ItemHideFlag): ItemStack
    {
        return ItemStack.builder(material)
                .meta { metadata ->
                    metadata.displayName(Components.fromLegacy(displayName))
                            .customModelData(customModelData)
                            .lore(*lores.map { s -> Components.fromLegacy(s) }
                                    .toTypedArray())
                            .hideFlag(*hideFlag)
                            .build()
                }
                .build()
    }

    fun newHead(uuid: UUID, displayName: String, lores: List<String> = listOf(), vararg hideFlag: ItemHideFlag): ItemStack
    {
        return ItemStack.builder(Material.PLAYER_HEAD)
                .meta(PlayerHeadMeta::class.java, Consumer { metadata ->
                    metadata.skullOwner(uuid)
                            .displayName(Components.fromLegacy(displayName))
                            .lore(*lores.map { s -> Components.fromLegacy(s) }
                                    .toTypedArray())
                            .hideFlag(*hideFlag)
                            .build()
                })
                .build()
    }

    fun newHead(texture: String, displayName: String, lores: List<String> = listOf(), vararg hideFlag: ItemHideFlag): ItemStack =
            ItemStack.builder(Material.PLAYER_HEAD)
                    .meta(PlayerHeadMeta::class.java, Consumer { metadata ->
                        metadata.playerSkin(PlayerSkin(texture, ""))
                                .displayName(Components.fromLegacy(displayName))
                                .lore(*lores.map { s -> Components.fromLegacy(s) }
                                        .toTypedArray())
                                .hideFlag(*hideFlag)
                                .build()
                    })
                    .build()
}