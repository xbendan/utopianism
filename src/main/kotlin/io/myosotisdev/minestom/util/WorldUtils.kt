package io.myosotisdev.minestom.util

import net.minestom.server.MinecraftServer
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.Instance


object WorldUtils
{
    fun loadAnvilWorld(path: String): Instance
    {
        val anvilLoader = AnvilLoader(path);
        val instanceContainer = MinecraftServer.getInstanceManager()
                .createInstanceContainer()
        instanceContainer.chunkLoader = anvilLoader
        return instanceContainer
    }
}