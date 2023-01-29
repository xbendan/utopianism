package io.myosotisdev.utopianism

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.module.*
import io.myosotisdev.minestom.util.WorldUtils
import io.myosotisdev.utopianism.modules.*
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.GenerationUnit

object Entry
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        val server = Minestom.start("0.0.0.0", 25565)

        Server()

        val instMgr = MinecraftServer.getInstanceManager()
        val instCont = instMgr.createInstanceContainer()
        instCont.setGenerator { world: GenerationUnit ->
            world.modifier()
                    .fillHeight(0, 40, Block.GRASS_BLOCK)
        }
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()
        val instance = WorldUtils.loadAnvilWorld("test-world")
        globalEventHandler.addListener(PlayerLoginEvent::class.java) { event: PlayerLoginEvent ->
            val player = event.player
            event.setSpawningInstance(instance)
            player.sendMessage("Hello, World!")
            player.respawnPoint = Pos(0.0, 42.0, 0.0)
        }
    }
}