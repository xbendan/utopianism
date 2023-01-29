package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.player.money
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.ArgumentWord
import net.minestom.server.command.builder.arguments.minecraft.ArgumentEntity
import net.minestom.server.command.builder.arguments.number.ArgumentDouble
import net.minestom.server.entity.Player

class EconomyCommand : AbstractCommand(null, Name, *Aliases)
{
    companion object
    {
        val Name: String = "economy"
        val Aliases: Array<String> = arrayOf("eco", "money")
        val arg0_operation: ArgumentWord = ArgumentType.Word("operation").from("set", "take", "give")
        val arg1_amount: ArgumentDouble = ArgumentType.Double("amount")
        val arg2_player: ArgumentEntity = ArgumentType.Entity("player").onlyPlayers(true)
    }

    init
    {
        addSyntax({ sender, context ->
            if(sender is Player)
            {
                sender.sendMessage(Components.fromLegacy("&a目前的账户余额: &e${sender.money}G"))
            }
        })
        addSyntax({ sender, context ->
            val econ = Server.getEconomy((sender as Player).uuid) ?: return@addSyntax
            val origin = econ.money
            Server.vaults().adjust(econ, context[arg0_operation], context[arg1_amount])
            sender.sendMessage(Components.fromLegacy("&a玩家持有的金币已被调整，从 &e${origin}G &a变更为 &e${econ.money}G"))
        }, permission = Permission(Namespaces.Utopianism, "economy.admin"), allowConsole = false, arg0_operation, arg1_amount)
        addSyntax({ sender, context ->
            val player = context[arg2_player].findFirstPlayer(sender)
            if(player != null)
            {
                val econ = Server.getEconomy(player.uuid) ?: return@addSyntax
                val origin = econ.money
                Server.vaults().adjust(econ, context[arg0_operation], context[arg1_amount])
                sender.sendMessage(Components.fromLegacy("&a玩家持有的金币已被调整，从 &e${origin}G &a变更为 &e${econ.money}G"))
            }
            else
                sender.sendMessage(Components.fromLegacy("&4玩家不存在"))
        }, permission = Permission(Namespaces.Utopianism, "economy.admin"), allowConsole = true, arg0_operation, arg1_amount, arg2_player)
    }

    override fun permission(): Permission = Permission(Namespaces.Utopianism, "economy")
}