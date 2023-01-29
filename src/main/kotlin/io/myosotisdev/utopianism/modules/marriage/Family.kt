package io.myosotisdev.utopianism.modules.marriage

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import io.myosotisdev.utopianism.modules.chat.ChatChannel
import java.util.UUID

class Family : IJsonData
{
    var name: String
    val uuid: UUID
    val chat: ChatChannel = ChatChannel()
    val familyMembers: ArrayList<UUID> = ArrayList()

    constructor(uuid: UUID, jsonObject: JsonObject)
    {
        this.uuid = uuid
        name = jsonObject["name"].asString

        jsonObject["members"].asJsonArray.forEach { element -> familyMembers.add(UUID.fromString(element.asString)) }
    }

    override fun asJsonCopy(): JsonElement
    {
        TODO("Not yet implemented")
    }

}