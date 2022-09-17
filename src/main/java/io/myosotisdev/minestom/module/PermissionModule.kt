package io.myosotisdev.minestom.module

import io.myosotisdev.minestom.permission.PermissionGroup
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import java.util.*
import kotlin.collections.HashSet

class PermissionModule : Module("permission")
{
    private val operators: MutableSet<UUID> = HashSet()
    val groups: MutableSet<PermissionGroup> = HashSet()

    fun setOp(player: Player?, enable: Boolean)
    {
        if (player == null) return
        if (enable)
        {
            operators.add(player.uuid)
            player.sendMessage("You have been set to operator.")
        }
        else
        {
            operators.remove(player.uuid)
            player.sendMessage("You are not operator any more")
        }
    }

    fun isOp(sender: CommandSender?): Boolean
    {
        return sender is Player && isOp(sender)
    }

    fun isOp(player: Player): Boolean
    {
        return operators.contains(player.uuid)
    }

    override fun onEnable()
    {
    }

    override fun onDisable()
    {
    }
}