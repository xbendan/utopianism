package io.myosotisdev.utopianism.modules.mailbox

import io.myosotisdev.utopianism.modules.player.PlayerEx

abstract class MailAttachment
{
    abstract fun isAvailable(): Boolean
    abstract fun apply(player: PlayerEx);
}