package io.myosotisdev.utopianism.modules.mailbox

interface IMailHost
{
    val mailbox: Mailbox

    fun sendMail(sender: IMailHost, mail: Mail)

    fun checkIfOutdated()
}