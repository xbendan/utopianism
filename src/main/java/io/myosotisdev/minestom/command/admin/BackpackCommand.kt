package io.myosotisdev.minestom.command.admin

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.permission.Permission.Companion.ofString
import io.myosotisdev.minestom.util.command.Commands.withPerm
import io.myosotisdev.utopianism.util.Namespaces
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.tag.Tag

class BackpackCommand(command: AbstractCommand?) : AbstractCommand(command, Name, *Aliases)
{
    init
    {
        addSubcommand(View(this))
        addSubcommand(Clear(this))
    }

    override fun permission(): Permission?
    {
        return ofString(Namespaces.Minestom, "admin.backpack")
    }

    internal class View(command: AbstractCommand?) : AbstractCommand(command, Name)
    {
        private val arg0_player = ArgumentType.Entity("player")
                .singleEntity(true)
                .onlyPlayers(true)

        init
        {
            addConditionalSyntax(withPerm(permission()), { sender: CommandSender, context: CommandContext ->
                val player = context.get(arg0_player)
                        .findFirstPlayer(sender)
                if (player != null)
                {
                    val viewInventory = Inventory(InventoryType.CHEST_4_ROW, player.name)
                    val originInventory = player.inventory
                    for (slotId in 0..35) viewInventory.setItemStack(slotId, originInventory.getItemStack(slotId))
                    viewInventory.setTag(Tag.Boolean("inventory_clickable"), false)
                    player.openInventory(viewInventory)
                }
                else sender.sendMessage(Component.text("Player does not exist!", NamedTextColor.RED))
            }, arg0_player)
        }

        override fun permission(): Permission?
        {
            return parent!!.permission()
        }

        companion object
        {
            const val Name = "view"
        }
    }

    internal class Clear(command: AbstractCommand?) : AbstractCommand(command, Companion.Name)
    {
        private val arg0_player = ArgumentType.Entity("player")
                .singleEntity(true)
                .onlyPlayers(true)

        override fun permission(): Permission?
        {
            return parent!!.permission()
        }

        companion object
        {
            const val Name = "clear"
        }
    }

    companion object
    {
        const val Name = "backpack"
        val Aliases = arrayOf(
                "pack",
                "bp"
        )
    }
}