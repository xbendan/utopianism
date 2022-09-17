package io.myosotisdev.utopianism.command

import io.myosotisdev.minestom.command.AbstractCommand
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.util.Namespaces

class GuildCommand : AbstractCommand{
    companion object {
        val Name: String = "guild"
        val Aliases: Array<String> = arrayOf()
    }

    constructor(command: AbstractCommand?) : super(command, Name)
    {
        addSubcommand(Create(this))
    }

    override fun permission(): Permission {
        return Permission.ofString(Namespaces.Utopianism, Name)
    }

    class Create(command: AbstractCommand) : AbstractCommand(command, Name)
    {
        companion object {
            const val Name: String = "create"
        }

        init
        {

        }

        override fun permission(): Permission {
            return Permission.ofString(Namespaces.Utopianism, "guild.create")
        }
    }

    class Dissolve(command: AbstractCommand) : AbstractCommand(command, Name)
    {
        companion object { val Name: String = "dissolve" }

        init
        {
            
        }

        override fun permission(): Permission {
            return Permission.ofString(Namespaces.Utopianism, "guild.dissolve")
        }
    }
}