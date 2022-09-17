package io.myosotisdev.utopianism.command.guild

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.number.ArgumentInteger

class GuildBankCommand : AbstractCommand
{
    companion object
    {
        val Name: String = "bank"
        val Aliases: Array<String> = arrayOf(
                "economy",
                "eco",
                "money"
        )
    }

    constructor(command: AbstractCommand) : super(command, Name, *Aliases)
    {
        addSubcommand(Deposit(this))
    }

    override fun permission(): Permission
    {
        return Permission.ofString(Namespaces.Utopianism, "guild.bank")
    }

    class Deposit : AbstractCommand
    {
        companion object { val Name: String = "deposit" }
        val arg0_amount: ArgumentInteger = ArgumentType.Integer("amount")

        constructor(command: AbstractCommand) : super(command, Name)
        {
            addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->

            }, arg0_amount)
        }

        override fun permission(): Permission { return Permission.ofString(Namespaces.Utopianism, "guild.bank.deposit") }
    }

    class Withdraw : AbstractCommand
    {
        companion object { val Name: String = "withdraw" }
        val arg0_amount: ArgumentInteger = ArgumentType.Integer("amount")

        constructor(command: AbstractCommand) : super(command, Name)
        {
            addConditionalSyntax(Commands.withPerm(permission()), CommandExecutor { sender, context ->

            }, arg0_amount)
        }

        override fun permission(): Permission { return Permission.ofString(Namespaces.Utopianism, "guild.bank.withdraw") }
    }
}