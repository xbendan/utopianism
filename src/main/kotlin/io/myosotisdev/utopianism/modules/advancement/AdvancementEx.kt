package io.myosotisdev.utopianism.modules.advancement

import io.myosotisdev.utopianism.modules.task.Reward
import net.kyori.adventure.text.Component
import net.minestom.server.advancements.Advancement
import net.minestom.server.advancements.FrameType
import net.minestom.server.item.Material

class AdvancementEx(title: Component, description: Component, icon: Material, frameType: FrameType, x: Float, y: Float) : Advancement(title, description, icon, frameType, x, y)
{
    val rewards: ArrayList<Reward> = ArrayList()
}