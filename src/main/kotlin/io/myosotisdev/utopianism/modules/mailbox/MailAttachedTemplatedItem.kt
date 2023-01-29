package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.item.ItemStackTemplate
import io.myosotisdev.utopianism.modules.item.Items
import io.myosotisdev.utopianism.modules.player.PlayerEx

class MailAttachedTemplatedItem(val template: ItemStackTemplate?) : MailAttachment()
{
    constructor(jsonObject: JsonObject) : this(Items.getByName(jsonObject["item"].asString))

    override fun isAvailable(): Boolean
    {
        return template != null && Items.getByName(template?.name.toString()) != null
    }

    override fun apply(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }

}