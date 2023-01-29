package io.myosotisdev.utopianism.modules.mailbox

import com.google.gson.JsonObject

class Mail(title: String, content: List<String>, sender: String, isFromPlayer: Boolean, sendTime: Long, expire: Long, vararg attachments: MailAttachment)
{
    constructor(jsonObject: JsonObject) : this(jsonObject["title"].asString, jsonObject["content"].asJsonArray.map { element -> element.asString }, jsonObject["sender"].asString, jsonObject["fromPlayer"].asBoolean, jsonObject["sendTime"].asLong, jsonObject["expire"].asLong, *jsonObject["attachments"].asJsonArray.map { element ->
        val attachment: JsonObject = element.asJsonObject
        when (MailAttachmentType.valueOf(attachment["type"].asString.uppercase()))
        {
            MailAttachmentType.ITEM           -> MailAttachedItemStack(attachment)
            MailAttachmentType.MONEY          -> MailAttachedMoney(attachment)
            MailAttachmentType.PERMISSION     -> MailAttachedPermission(attachment)
            MailAttachmentType.TEMPLATED_ITEM -> MailAttachedTemplatedItem(attachment)
        }
    }
            .toTypedArray())
}