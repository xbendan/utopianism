package io.myosotisdev.utopianism.util

import io.myosotisdev.minestom.extension.InstanceExtension.spawnParticle
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.network.packet.client.play.ClientAnimationPacket
import net.minestom.server.network.packet.server.SendablePacket
import net.minestom.server.network.packet.server.play.EntityAnimationPacket
import net.minestom.server.network.packet.server.play.EntityPropertiesPacket
import net.minestom.server.network.packet.server.play.EntityStatusPacket
import net.minestom.server.network.packet.server.play.SetSlotPacket
import net.minestom.server.particle.Particle

object Effects
{
    fun playTotemOfUndying(player: Player, customModelData: Int)
    {
        player.sendPacket(SetSlotPacket(0, 0, 45, ItemStack.builder(Material.TOTEM_OF_UNDYING)
                .meta { metadata -> metadata.customModelData(customModelData) }
                .build()))
        player.sendPacket(EntityStatusPacket(player.entityId, 35))
        player.inventory.update()
    }
}