package io.myosotisdev.utopianism.modules.marriage

import com.google.common.base.Strings
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.myosotisdev.minestom.IJsonData
import java.util.*

class MarriageData : IJsonData
{
    var uuid: UUID
    var partner: UUID? = null
    val isSingle: Boolean
        get() = Objects.isNull(partner)
    var gender: Gender = Gender.MALE
    var family: Family? = null

    constructor(uuid: UUID)
    {
        this.uuid = uuid
    }

    constructor(jsonObject: JsonObject) : this(UUID.fromString(jsonObject["uuid"].asString))
    {
        this.partner = if (Strings.isNullOrEmpty(jsonObject["partner"].asString)) UUID.fromString(jsonObject["partner"].asString) else null
        this.gender = Gender.valueOf(jsonObject["gender"].asString.uppercase())

        val familyString: String? = jsonObject["family"].asString
//        if(!Strings.isNullOrEmpty(familyString))
//            this.family = Marriages.getFamily(UUID.fromString(familyString))
    }

    override fun asJsonCopy(): JsonElement
    {
        val jsonObject = JsonObject()

        jsonObject.addProperty("uuid", uuid.toString())
        jsonObject.addProperty("partner", partner?.toString() ?: "")
        jsonObject.addProperty("gender", gender.toString()
                .lowercase())
        jsonObject.addProperty("family", family?.uuid.toString())

        return jsonObject
    }
}