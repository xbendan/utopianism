package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.player.PlayerEx

class MailAttachedItemStack : MailAttachment
{
    constructor(jsonObject: JsonObject)
    {

    }

    override fun isAvailable(): Boolean = true

    override fun apply(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }

}