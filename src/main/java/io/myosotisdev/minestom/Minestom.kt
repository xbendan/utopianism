package io.myosotisdev.minestom

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.myosotisdev.minestom.module.Module
import io.myosotisdev.minestom.module.PermissionModule
import io.myosotisdev.minestom.permission.IPermissionHandler
import io.myosotisdev.minestom.permission.Permission
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.MinecraftServer
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.entity.fakeplayer.FakePlayer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.network.ConnectionManager
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.SchedulerManager
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.util.*
import java.util.function.Consumer

object Minestom
{
    private val Modules: MutableMap<String, Module?> = HashMap()
    private var Server: MinecraftServer? = null
    private var ServerProperties: JsonObject? = null
    fun getModule(name: String): Module?
    {
        return Modules[name]
    }

    fun registerModule(module: Module): Boolean
    {
        val result = Modules.putIfAbsent(module.name, module) == null
        if (result && MinecraftServer.isStarted()) module.onEnable()
        return result
    }

    fun registerModules(vararg modules: Module)
    {
        for (mod in modules) registerModule(mod)
    }

    fun unregisterModule(name: String)
    {
        Modules.remove(name)
    }

    fun unregisterModules()
    {
        Modules.clear()
    }

    fun overrideModule(module: Module)
    {
        Modules[module.name] = module
    }

    fun modules(): Collection<Module?>
    {
        return Modules.values
    }

    val connectionManager: ConnectionManager
        get() = MinecraftServer.getConnectionManager()

    @JvmStatic
    fun registerCommand(command: Command?)
    {
        MinecraftServer.getCommandManager()
                .register(command!!)
    }

    fun registerCommands(vararg commands: Command?)
    {
        for (command in commands) MinecraftServer.getCommandManager()
                .register(command!!)
    }

    fun getPlayer(name: String): Player?
    {
        return connectionManager.getPlayer(name)
    }

    fun getPlayer(uuid: UUID): Player?
    {
        return connectionManager.getPlayer(uuid)
    }

    fun getOnlinePlayers(): Collection<Player>
    {
        return connectionManager.onlinePlayers
    }

    fun <T : Event?> registerListener(listener: EventListener<T>)
    {
        MinecraftServer.getGlobalEventHandler()
                .addListener(listener)
    }

    @JvmStatic
    fun <T : Event?> registerListener(eventClass: Class<T>, listener: Consumer<T>?)
    {
        registerListener(EventListener.builder(eventClass)
                .handler(listener)
                .build())
    }

    val scheduler: SchedulerManager
        get() = MinecraftServer.getSchedulerManager()

    fun scheduleTask(runnable: Runnable?, delay: Int, repeat: Int): Task
    {
        return scheduleTask(runnable, TaskSchedule.tick(delay), TaskSchedule.tick(repeat), ExecutionType.SYNC)
    }

    fun scheduleTaskAsync(runnable: Runnable?, delay: Int, repeat: Int): Task
    {
        return scheduleTask(runnable, TaskSchedule.tick(delay), TaskSchedule.tick(repeat), ExecutionType.ASYNC)
    }

    fun scheduleTask(runnable: Runnable?, delay: TaskSchedule?, repeat: TaskSchedule?, type: ExecutionType?): Task
    {
        return scheduler.scheduleTask(runnable!!, delay!!, repeat!!, type!!)
    }

    fun isOp(sender: CommandSender?): Boolean
    {
        return sender is Player && isOp(sender as Player?)
    }

    fun isOp(player: Player?): Boolean
    {
        val pm = getModule("permission")
        return if (pm != null) (pm as PermissionModule).isOp(player) else true
    }

    fun setOp(player: Player?, enable: Boolean)
    {
        val pm = getModule("permission")
        if (pm != null) (pm as PermissionModule).setOp(player, enable)
    }

    fun hasPermission(handler: IPermissionHandler, permission: Permission?): Boolean
    {
        return handler.hasPerm(permission)
    }

    @JvmStatic
    fun checkPermission(sender: CommandSender, permission: Permission?): Boolean
    {
        return sender is ConsoleSender || isOp(sender) || sender.hasPermission(permission!!) || sender is FakePlayer
    }

    fun broadcast(component: Component?)
    {
        Audiences.all()
                .sendMessage(component!!)
    }

    fun properties(): JsonObject?
    {
        if (ServerProperties == null)
        {
            val file = File("minestom.json")
            try
            {
                if (!file.exists())
                {
                    val `in` = Minestom::class.java.getResourceAsStream("minestom.json")
                    val out = FileOutputStream(file)
                    val fileBytes = ByteArray(1)
                    while (`in`.read(fileBytes) != -1) out.write(fileBytes)
                    out.flush()
                }
                ServerProperties = JsonParser.parseReader(FileReader(file))
                        .asJsonObject
            }
            catch (e: IOException)
            {
                throw RuntimeException(e)
            }
        }
        return ServerProperties
    }

    fun start(): MinecraftServer?
    {
        val addressObject = ServerProperties!!["address"]
                .asJsonObject
        return start(addressObject["ip"]
                .asString, addressObject["port"]
                .asInt)
    }

    fun start(hostIP: String?, port: Int): MinecraftServer?
    {
        if (Server != null && MinecraftServer.isStarted()) return Server
        MinecraftServer.init()
                .also { Server = it }
                .start(hostIP!!, port)
        modules().stream()
                .forEach { module: Module? -> module!!.onEnable() }
        return Server
    }

    @JvmStatic
    fun stop()
    {
        broadcast(Component.text("Server stopping...", TextColor.color(170), TextDecoration.ITALIC))
        //
        MinecraftServer.stopCleanly()
    }
}