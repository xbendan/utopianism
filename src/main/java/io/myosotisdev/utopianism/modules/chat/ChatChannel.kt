package io.myosotisdev.utopianism.modules.chat

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.MessageType
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import net.minestom.server.entity.Player
import java.util.*

class ChatChannel : Audience
{
    val audiences: List<Player> = ArrayList()

    override fun sendMessage(source: Identity, message: Component, type: MessageType) = audiences.stream().forEach { it.sendMessage(source, message, type) }

    override fun showTitle(title: Title) = audiences.stream().forEach { it.showTitle(title) }

    override fun clearTitle() = audiences.stream().forEach { it.clearTitle() }

    override fun resetTitle() = audiences.stream().forEach { it.resetTitle() }

    override fun showBossBar(bar: BossBar) = audiences.stream().forEach { it.showBossBar(bar) }

    override fun hideBossBar(bar: BossBar) = audiences.stream().forEach { it.hideBossBar(bar) }

    override fun playSound(sound: Sound) = audiences.stream().forEach { it.playSound(sound) }

    override fun playSound(sound: Sound, x: Double, y: Double, z: Double) = audiences.stream().forEach { it.playSound(sound, x, y, z) }

    override fun playSound(sound: Sound, emitter: Sound.Emitter) = audiences.stream().forEach { it.playSound(sound, emitter) }

    override fun stopSound(stop: SoundStop) = audiences.stream().forEach { it.stopSound(stop) }

    override fun openBook(book: Book) = audiences.stream().forEach { it.openBook(book) }
}