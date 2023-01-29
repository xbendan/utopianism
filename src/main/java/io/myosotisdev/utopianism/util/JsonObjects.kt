package io.myosotisdev.utopianism.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.stream.JsonWriter
import io.myosotisdev.minestom.IJsonData
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object JsonObjects
{
    val gson = Gson()

    fun asJsonCopy(data: IJsonData): JsonElement = data.asJsonCopy()

    fun asJsonCopy(position: Pos, instance: Instance): JsonElement
    {
        val jsonObject = JsonObject()

        jsonObject.addProperty("x", position.x)
        jsonObject.addProperty("y", position.y)
        jsonObject.addProperty("z", position.z)
        jsonObject.addProperty("yaw", position.yaw)
        jsonObject.addProperty("pitch", position.pitch)
        jsonObject.addProperty("world", instance.uniqueId.toString())

        return jsonObject
    }

    fun readFile(file: File): JsonElement?
    {
        if(!file.exists())
        {
            return null
        }

        return JsonParser.parseReader(FileReader(file))
    }

    fun readFile(path: String): JsonElement? = readFile(File(path))

    fun writeFile(jsonElement: JsonElement, file: File)
    {
        if(!file.exists())
        {
            file.createNewFile()
        }

        gson.toJson(jsonElement, JsonWriter(FileWriter(file)))
    }
}