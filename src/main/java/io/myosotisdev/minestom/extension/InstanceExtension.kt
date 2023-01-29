package io.myosotisdev.minestom.extension

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.network.packet.server.play.ParticlePacket
import net.minestom.server.particle.Particle
import net.minestom.server.particle.ParticleCreator
import java.util.*

object InstanceExtension
{
    fun Instance.spawnParticle(particle: Particle, location: Pos, count: Int = 1, canSee: Collection<Player?>?)
    {
        spawnParticle(particle, location.x, location.y, location.z, count = count, canSee = canSee)
    }

    fun Instance.spawnParticle(particle: Particle, x: Double, y: Double, z: Double, offsetX: Float = 0.0f, offsetY: Float = 0.0f, offsetZ: Float = 0.0f, count: Int = 1, canSee: Collection<Player?>?)
    {
        val packet: ParticlePacket = ParticleCreator.createParticlePacket(particle, x, y, z, offsetX, offsetY, offsetZ, count)

        canSee?.stream()
                ?.forEach { player -> player?.sendPacket(packet) }
    }
}