package io.myosotisdev.minestom.module

import com.google.common.base.Strings
import io.myosotisdev.minestom.Minestom.registerCommand
import io.myosotisdev.minestom.Minestom.registerListener
import net.minestom.server.command.builder.Command
import net.minestom.server.event.Event
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.function.Consumer
import java.util.logging.Level
import java.util.logging.Logger

abstract class Module(val name: String)
{
    val logger: Logger

    init
    {
        logger = Logger.getLogger(name)
    }

    val dataFolder: File
        get()
        {
            var file: File
            File(name).also { file = it }
                    .mkdir()
            return file
        }

    @Throws(IOException::class)
    fun saveConfig(): File?
    {
        return savePackagedResource("configuration.json", false)
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun savePackagedResource(path: String, override: Boolean = false): File?
    {
        require(!Strings.isNullOrEmpty(path)) { "Path cannot null or empty" }
        val fileInDestination = File(dataFolder, path)
        var packageFileStream: InputStream
        if (!fileInDestination.exists() || override)
        {
            fileInDestination.delete()
            fileInDestination.createNewFile()
            if (getPackagedResource(path).also { packageFileStream = it!! } != null)
            {
                val out = FileOutputStream(fileInDestination)
                val bytes = ByteArray(1)
                while (packageFileStream.read(bytes) != -1) out.write(bytes)
                out.flush()
            }
            else return null
        }
        return fileInDestination
    }

    fun getPackagedResource(path: String): InputStream?
    {
        return try
        {
            val url = javaClass.classLoader.getResource("$name/$path")
            url?.openConnection()
                    ?.getInputStream()
        }
        catch (var3: IOException)
        {
            logger.log(Level.WARNING, String.format("Failed to load resource {}.", path))
            null
        }
    }

    fun addCommand(command: Command?)
    {
        registerCommand(command)
    }

    fun <T : Event?> addListener(clazz: Class<T>, consumer: Consumer<T>?)
    {
        registerListener(clazz, consumer)
    }

    abstract fun onEnable()
    abstract fun onDisable()
}