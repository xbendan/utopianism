package io.myosotisdev.utopianism.modules.teleport

import com.google.gson.JsonElement
import io.myosotisdev.minestom.IJsonData
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance

class AnchorPoint(val instance: Instance?, val position: Pos) : IJsonData
{
    val onlyPosition: Boolean = false

    override fun asJsonCopy(): JsonElement
    {
        TODO("Not yet implemented")
    }
}