package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.player.PlayerEx

class MailAttachedMoney(val money: Double) : MailAttachment()
{
    constructor(jsonObject: JsonObject) : this(jsonObject["amount"].asDouble)

    override fun isAvailable(): Boolean = true

    override fun apply(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }
}