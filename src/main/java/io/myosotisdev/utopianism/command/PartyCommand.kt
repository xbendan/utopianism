package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.minestom.util.command.Commands
import io.myosotisdev.utopianism.util.Namespaces
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.entity.Player

class PartyCommand : AbstractCommand
{
    companion object
    {
        val Name: String = "party"
    }

    constructor(command: AbstractCommand?) : super(command, Name)
    {
        addSubcommand(Create(this))
        addSubcommand(Dissolve(this))
    }

    override fun permission(): Permission
    {
        return Permission.ofString(Namespaces.Utopianism, "party")
    }

    class Create(command: AbstractCommand?) : AbstractCommand(command, Name)
    {
        companion object { val Name: String = "create" }

        override fun permission(): Permission
        {
            return Permission.ofString(Namespaces.Utopianism, "party.create")
        }
    }

    class Leave : AbstractCommand
    {
        companion object { val Name: String = "leave" }

        constructor(command: AbstractCommand) : super(command, Name)
        {

        }

        override fun permission(): Permission? { return parent?.permission() }
    }

    class Invite : AbstractCommand
    {
        companion object
        {
            val Name: String = "invite"
        }

        constructor(command: AbstractCommand) : super(command, Invite.Name)
        {

        }

        override fun permission(): Permission
        {
            return Permission.ofString(Namespaces.Utopianism, "party.invite")
        }
    }

    class Join : AbstractCommand
    {
        companion object
        {
            val Name: String = "leave"
        }

        constructor(command: AbstractCommand) : super(command, Leave.Name)
        {

        }

        override fun permission(): Permission
        {
            return Permission.ofString(Namespaces.Utopianism, "party.leave")
        }
    }

    class Dissolve : AbstractCommand
    {
        companion object
        {
            val Name: String = "dissolve";
        }

        constructor(command: AbstractCommand) : super(command, Dissolve.Name)
        {
            addConditionalSyntax(Commands.withPerm(permission()), { sender, context ->
                val player: Player = sender as Player

            })
        }

        override fun permission(): Permission?
        {
            return parent?.permission()
        }
    }
}