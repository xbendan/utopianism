package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonElement
import io.myosotisdev.minestom.IJsonData

class Mailbox : IJsonData
{
    val mails: ArrayList<Mail> = ArrayList()

    override fun asJsonCopy(): JsonElement
    {
        TODO("Not yet implemented")
    }
}