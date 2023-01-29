package io.myosotisdev.utopianism.modules.team

import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.serialize.Serializer
import java.util.*

class TeamTagSerializer : Serializer<Team>()
{
    override fun serialize(obj: Team?): String? = obj?.uuid.toString()

    override fun deserialize(string: String?): Team? = if (string != null) Server.teams().findByUuid(UUID.fromString(string)) else null
}