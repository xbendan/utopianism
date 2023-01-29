package io.myosotisdev.utopianism.modules.homeland

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.util.ItemStacks
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

object Homelands
{
    fun makeItemStack(land: Homeland): ItemStack = ItemStacks.new(Material.OAK_DOOR,
            "&a${Server.getCustomName(land.uuid)} &a的家园世界",
            listOf("&e${land.members.filter { Minestom.getPlayer(it) != null }.size}&7/${land.members.size} 位成员在线"))
}