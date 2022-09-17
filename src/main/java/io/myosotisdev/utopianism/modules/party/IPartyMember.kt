package io.myosotisdev.utopianism.modules.party

interface IPartyMember
{
    var party: Party?

    fun isPartyLeader(): Boolean

    fun leaveParty()
}