package io.myosotisdev.utopianism.modules.clan

enum class ClanClass(val text: String, val level: Int, val rolePerms: Array<ClanRolePerm>)
{
    NORMAL_MEMBER("&a正式成员", 0, arrayOf(ClanRolePerm.CHAT, ClanRolePerm.ALLY_CHAT, ClanRolePerm.DEPOSIT_MONEY, ClanRolePerm.INTERACT)),
    ELITE_MEMBER("&d精英成员", 1, arrayOf(ClanRolePerm.INVITE, ClanRolePerm.OPEN_VAULT, ClanRolePerm.WITHDRAW_MONEY, ClanRolePerm.PLACE, ClanRolePerm.DESTROY).plus(NORMAL_MEMBER.rolePerms)),
    MANAGER("&b领队", 2, arrayOf(ClanRolePerm.ADD_ALLY, ClanRolePerm.REMOVE_ALLY, ClanRolePerm.ACTIVATE_BUFF, ClanRolePerm.INITIATE_WAR).plus(ELITE_MEMBER.rolePerms)),
    VICE_PRESIDENT("&5&l副会长", 3, arrayOf(ClanRolePerm.KICK, ClanRolePerm.UPGRADE_GUILD, ClanRolePerm.MODIFY_MOTD).plus(MANAGER.rolePerms)),
    PRESIDENT("&a&l会长", 4, ClanRolePerm.values()),
}