package io.myosotisdev.utopianism

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.command.*
import io.myosotisdev.minestom.module.LocaleModule
import io.myosotisdev.minestom.module.PermissionModule
import io.myosotisdev.utopianism.command.GuildCommand
import io.myosotisdev.utopianism.command.PartyCommand
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit

object MainEntryPoint
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val server = Minestom.start("0.0.0.0", 25565)
        Minestom.registerModules(
                LocaleModule(),
                PermissionModule()
        )
        Minestom.registerCommands(
                AdminCommand(),
                KillCommand(),
                GameModeCommand(),
                ManageCommand(),
                PermissionCommand(),
                PartyCommand(null),
                GuildCommand(null)
        )
        val instMgr = MinecraftServer.getInstanceManager()
        val instCont = instMgr.createInstanceContainer()
        instCont.setGenerator { world: GenerationUnit ->
            world.modifier()
                    .fillHeight(0, 40, Block.GRASS_BLOCK)
        }
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()
        globalEventHandler.addListener(PlayerLoginEvent::class.java) { event: PlayerLoginEvent ->
            val player = event.player
            event.setSpawningInstance(instCont)
            player.sendMessage("Hello, World!")
            player.respawnPoint = Pos(0.0, 42.0, 0.0)
        }
    }
}