package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonObject
import io.myosotisdev.minestom.permission.Permission
import io.myosotisdev.utopianism.modules.player.PlayerEx

class MailAttachedPermission(permission: Permission) : MailAttachment()
{
    constructor(string: String) : this(Permission.ofString(string)!!)

    constructor(jsonObject: JsonObject) : this(jsonObject["permission"].asString)

    override fun isAvailable(): Boolean = true

    override fun apply(player: PlayerEx)
    {
        TODO("Not yet implemented")
    }
}