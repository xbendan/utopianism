package io.myosotisdev.minestom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.myosotisdev.minestom.module.Module;
import io.myosotisdev.minestom.module.PermissionModule;
import io.myosotisdev.minestom.permission.IPermissionHandler;
import io.myosotisdev.minestom.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.network.ConnectionManager;
import net.minestom.server.timer.ExecutionType;
import net.minestom.server.timer.SchedulerManager;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class Minestom
{
    private static Map<String, Module> Modules = new HashMap<>();
    private static MinecraftServer     Server;
    private static JsonObject          ServerProperties;

    public static Module getModule(String name)
    {
        return Modules.get(name);
    }

    public static boolean registerModule(Module module)
    {
        boolean result = (Modules.putIfAbsent(module.getName(), module)) == null;
        if (result && MinecraftServer.isStarted())
            module.onEnable();
        return result;
    }

    public static void registerModules(Module... modules)
    {
        for (Module mod : modules)
            registerModule(mod);
    }

    public static void unregisterModule(String name)
    {
        Modules.remove(name);
    }

    public static void unregisterModules()
    {
        Modules.clear();
    }

    public static void overrideModule(Module module)
    {
        Modules.put(module.getName(), module);
    }

    public static Collection<Module> modules()
    {
        return Modules.values();
    }

    public static ConnectionManager getConnectionManager()
    {
        return MinecraftServer.getConnectionManager();
    }

    public static void registerCommand(Command command)
    {
        MinecraftServer.getCommandManager()
                       .register(command);
    }

    public static void registerCommands(Command... commands)
    {
        for (Command command : commands)
            MinecraftServer.getCommandManager()
                           .register(command);
    }

    public static Player getPlayer(String name)
    {
        return getConnectionManager().getPlayer(name);
    }

    public static Player getPlayer(UUID uuid)
    {
        return getConnectionManager().getPlayer(uuid);
    }

    public static <T extends Event> void registerListener(EventListener<T> listener)
    {
        MinecraftServer.getGlobalEventHandler()
                       .addListener(listener);
    }

    public static <T extends Event> void registerListener(Class<T> eventClass, Consumer<T> listener)
    {
        registerListener(EventListener.builder(eventClass)
                                      .handler(listener)
                                      .build());
    }

    public static SchedulerManager getScheduler()
    {
        return MinecraftServer.getSchedulerManager();
    }

    public static Task scheduleTask(Runnable runnable, int delay, int repeat)
    {
        return scheduleTask(runnable, TaskSchedule.tick(delay), TaskSchedule.tick(repeat), ExecutionType.SYNC);
    }

    public static Task scheduleTaskAsync(Runnable runnable, int delay, int repeat)
    {
        return scheduleTask(runnable, TaskSchedule.tick(delay), TaskSchedule.tick(repeat), ExecutionType.ASYNC);
    }

    public static Task scheduleTask(Runnable runnable, TaskSchedule delay, TaskSchedule repeat, ExecutionType type)
    {
        return getScheduler().scheduleTask(runnable, delay, repeat, type);
    }

    public static boolean isOp(CommandSender sender)
    {
        return sender instanceof Player && isOp((Player) sender);
    }

    public static boolean isOp(Player player)
    {
        Module pm = getModule("permission");
        return pm != null ?
                ((PermissionModule) pm).isOp(player) :
                true;
    }

    public static void setOp(Player player, boolean enable)
    {
        Module pm = getModule("permission");
        if (pm != null)
            ((PermissionModule) pm).setOp(player, enable);
    }

    public static boolean hasPermission(IPermissionHandler handler, Permission permission)
    {
        return handler.hasPermission(permission);
    }

    public static boolean checkPermission(CommandSender sender, Permission permission)
    {
        return sender instanceof ConsoleSender || isOp(sender) || sender.hasPermission(permission) || sender instanceof FakePlayer;
    }

    public static void broadcast(Component component)
    {
        Audiences.all()
                 .sendMessage(component);
    }

    public static JsonObject properties()
    {
        if (ServerProperties == null)
        {
            File file = new File("minestom.json");
            try
            {
                if (!file.exists())
                {
                    InputStream in = Minestom.class.getResourceAsStream("minestom.json");
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] fileBytes = new byte[1];
                    while (in.read(fileBytes) != -1)
                        out.write(fileBytes);
                    out.flush();
                }
                ServerProperties = JsonParser.parseReader(new FileReader(file))
                                             .getAsJsonObject();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        return ServerProperties;
    }

    public static MinecraftServer start()
    {
        JsonObject addressObject = ServerProperties.get("address")
                                                   .getAsJsonObject();
        return start(addressObject.get("ip")
                                  .getAsString(), addressObject.get("port")
                                                               .getAsInt());
    }

    public static MinecraftServer start(String hostIP, int port)
    {
        if (Server != null && MinecraftServer.isStarted())
            return Server;

        (Server = MinecraftServer.init()).start(hostIP, port);

        modules().stream()
                 .forEach(module -> module.onEnable());
        return Server;
    }

    public static void stop()
    {
        broadcast(Component.text("Server stopping...", TextColor.color(170), TextDecoration.ITALIC));
        //
        MinecraftServer.stopCleanly();
    }
}
