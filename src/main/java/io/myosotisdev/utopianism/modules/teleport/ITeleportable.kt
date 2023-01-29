package io.myosotisdev.utopianism.modules.teleport

interface ITeleportable
{
    val teleportCooldowns: HashMap<AnchorPoint, Long>
}