package io.myosotisdev.utopianism.modules.clan

enum class ClanJoinPolicy(val text: String)
{
    FREE("自动同意"),
    CENSORSHIP_REQUIRED("需要审核"),
    CONDITION_REQUIRED("需满足条件"),
    NOT_ACCEPT("&4不接受新成员")
    ;
}