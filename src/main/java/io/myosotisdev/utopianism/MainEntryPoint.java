package io.myosotisdev.utopianism;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.minestom.command.GameModeCommand;
import io.myosotisdev.minestom.command.KillCommand;
import io.myosotisdev.minestom.command.ManageCommand;
import io.myosotisdev.minestom.command.PermissionCommand;
import io.myosotisdev.minestom.module.LocaleModule;
import io.myosotisdev.minestom.module.PermissionModule;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;

public class MainEntryPoint
{
    public static void main(String[] args)
    {
        MinecraftServer server = Minestom.start("0.0.0.0", 25565);

        Minestom.registerModules(
                new LocaleModule(),
                new PermissionModule()
        );

        Minestom.registerCommands(
                new KillCommand(),
                new GameModeCommand(),
                new ManageCommand(),
                new PermissionCommand()
        );

        InstanceManager instMgr = MinecraftServer.getInstanceManager();

        InstanceContainer instCont = instMgr.createInstanceContainer();

        instCont.setGenerator(world -> world.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event ->
        {
            Player player = event.getPlayer();
            event.setSpawningInstance(instCont);
            player.sendMessage("Hello, World!");
            player.setRespawnPoint(new Pos(0, 42, 0));
        });
    }
}
