package io.myosotisdev.minestom.module

import net.minestom.server.MinecraftServer
import net.minestom.server.instance.Instance
import net.minestom.server.instance.InstanceManager
import java.io.File

class WorldInstanceModule : ModuleManager("world")
{
    val worlds: HashMap<String, Instance> = HashMap()
    val templates: HashMap<String, File> = HashMap()
    val manager: InstanceManager
        get() = MinecraftServer.getInstanceManager()

    override fun onEnable()
    {
        TODO("Not yet implemented")
    }

    override fun onDisable()
    {
        TODO("Not yet implemented")
    }

    fun createTempWorld(): Instance
    {
        TODO()
    }

    fun createWorld(name: String): Instance
    {
        return createWorld(name, null)
    }

    fun createWorld(name: String, file: File?): Instance
    {
        if(file != null)
        {
            file.mkdirs()
        }

        TODO()
    }

    fun loadWorld(file: File): Instance?
    {
        TODO()
    }

    fun unloadWorld(name: String)
    {
        TODO()
    }
}