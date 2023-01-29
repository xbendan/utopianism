package io.myosotisdev.utopianism.listeners

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.util.Components
import io.myosotisdev.utopianism.Server
import io.myosotisdev.utopianism.modules.clan.*
import io.myosotisdev.utopianism.modules.player.clan
import io.myosotisdev.utopianism.modules.player.money
import io.myosotisdev.utopianism.modules.team.Team
import io.myosotisdev.utopianism.ui.generic.*
import net.kyori.adventure.text.Component
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.player.PlayerChatEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.click.ClickType
import net.minestom.server.item.Material
import java.util.*
import java.util.function.Function

class InteractivityEvents(val sys: Server)
{
    val chatFormat: Function<PlayerChatEvent, Component> = Function<PlayerChatEvent, Component> { event ->
        val player = event.player
        val displayName = Server.getCustomName(player.uuid)

        val text = "${
            if (player.clan != null) "&7&l[&a${player.clan!!.name}&7&l]" else ""
        } &6[${Server.getPlayerData(player).getBattleLevel()}★] $displayName &6&l>> &f${event.message}"

        return@Function Components.fromLegacy(text)
    }
    val ChatPendingActions: HashMap<UUID, ChatAction> = HashMap()

    init
    {
        Minestom.registerListener(InventoryPreClickEvent::class.java) { event ->
            if (event.inventory == null || !(event.inventory is VisualInventory)) return@registerListener

            val inventory: Inventory = event.inventory!!
            val clickType = event.clickType
            val player = event.player

            event.isCancelled = true

            when (inventory)
            {
                is VisualClanCreate -> when (event.slot)
                {
                    11 ->
                    {
                        ChatPendingActions[player.uuid] = ChatAction.GUILD_PENDING_CREATE
                        player.sendMessage(Components.fromLegacy("&a请在聊天框输入公会名称&f（无需\'/\'）"))
                        player.closeInventory()
                    }
                    15 ->
                    {
                        if (clickType == ClickType.LEFT_CLICK) player.openInventory(VisualClanList())
                    }
                }
                is VisualClanList   -> when (event.slot)
                {
                    45 ->
                    {
                        if (!event.clickedItem.isAir)
                        {
                            inventory.loadPage(inventory.page - 1)
                            inventory.update()
                        }
                    }
                    53 ->
                    {
                        if (!event.clickedItem.isAir)
                        {
                            inventory.loadPage(inventory.page + 1)
                            inventory.update()
                        }
                    }
                }
                is VisualClan       ->
                {
                    val clan = inventory.clan
                    val clanMember = clan.members[player.uuid] ?: return@registerListener
                    when (event.slot)
                    {
                        20 ->
                        {
                            if (clickType == ClickType.LEFT_CLICK) player.openInventory(VisualClanMembers(player))
                        }
                        21 -> when (clickType)
                        {
                            ClickType.LEFT_CLICK ->
                            {
                                if (clanMember.perms[ClanRolePerm.DEPOSIT_MONEY] != true)
                                {
                                    player.sendMessage("&c你不被允许向公会银行里存款")
                                    return@registerListener
                                }

                                ChatPendingActions[player.uuid] = ChatAction.GUILD_BANK_DEPOSIT
                                player.closeInventory()
                                player.sendMessage("&a请输入存款额度")
                            }
                            ClickType.RIGHT_CLICK ->
                            {
                                if (clanMember.perms[ClanRolePerm.WITHDRAW_MONEY] != true)
                                {
                                    player.sendMessage("&c你不被允许从公会银行里取款")
                                    return@registerListener
                                }

                                ChatPendingActions[player.uuid] = ChatAction.GUILD_BANK_WITHDRAW
                                player.closeInventory()
                                player.sendMessage("&a请输入取款额度")
                            }
                            else ->
                            {
                            }
                        }
                        22 -> when (clickType)
                        {
                            ClickType.SHIFT_CLICK ->
                            {
                                if (clanMember.level.level < ClanClass.VICE_PRESIDENT.level)
                                {
                                    player.sendMessage("&c只有副会长和会长才能升级公会")
                                    return@registerListener
                                }

                                val rankupCost = clan.tier.cost

                                if (rankupCost <= 0)
                                {
                                    player.sendMessage("&c公会已经达到满级了，请等待开放上限")
                                    return@registerListener
                                }

                                if (clan.bank.money < rankupCost)
                                {
                                    player.sendMessage("&c公会没有足够的资金进行升级")
                                    return@registerListener
                                }

                                clan.tier = ClanTier.values()[clan.tier.level + 1];
                                clan.broadcast("&a已经升级为${clan.tier.name}公会")
                            }
                            else                 ->
                            {
                            }
                        }
                        23 ->
                        {
                            if (clickType == ClickType.LEFT_CLICK)
                            {
                                val values = ClanJoinPolicy.values()
                                val index = values.indexOf(clan.joinPolicy)
                                clan.joinPolicy = values[if (index == values.size - 1) 0 else index + 1]

                                inventory.slots(4, 23)
                            }
                            else if (clickType == ClickType.RIGHT_CLICK)
                            {

                            }
                        }
                        24 ->
                        {
                            if (clickType == ClickType.LEFT_CLICK)
                            {
                                val values = ClanPlayPolicy.values()
                                val index = values.indexOf(clan.playPolicy)
                                clan.playPolicy = values[if (index == values.size - 1) 0 else index + 1]

                                inventory.slots(4, 24)
                            }
                        }
                    }
                }
                is VisualPlayerData  -> when (event.slot)
                {
                    12 -> player.openInventory(if (event.clickedItem.material() == Material.FURNACE) VisualClanCreate()
                    else VisualClan(player))
                    13 ->
                    {
                        if(player.getTag(Team.TeamTag) != null)
                            player.openInventory(VisualTeam(player))
                        else when(clickType)
                        {
                            ClickType.LEFT_CLICK ->
                            {
                                val team = Server.teams().createTeam(player)
                                if(Objects.isNull(team))
                                {
                                    player.sendMessage("&c创建失败，你已经有一个队伍了")
                                    return@registerListener
                                }
                                player.sendMessage("&a队伍已创建，将邀请码 &e${team?.inviteCode} &a")
                                inventory.slots(13)
                            }
                            ClickType.RIGHT_CLICK ->
                            {
                                ChatPendingActions[player.uuid] = ChatAction.PARTY_CODE
                                player.sendMessage("&a请在聊天框输入队伍邀请码&f（无需'/'）")
                                player.closeInventory()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

        Minestom.registerListener(PlayerChatEvent::class.java) { event ->
            val player = event.player
            val msg = event.message
            val action = ChatPendingActions[player.uuid]
            if (action != null)
            {
                event.isCancelled = true
                if (msg.equals("cancel", ignoreCase = true))
                {
                    ChatPendingActions.remove(player.uuid)
                    player.sendMessage("&a操作已取消")
                    return@registerListener
                }
                when (action)
                {
                    ChatAction.GUILD_PENDING_CREATE ->
                    {
                        if (player.clan != null)
                        {
                            player.sendMessage("&c你已经在一个公会里了，不能重复创建！")
                            return@registerListener
                        }

                        if (player.money < ClanVars.CreationCost)
                        {
                            player.sendMessage("&c余额不足，需要 &e${ClanVars.CreationCost}G")
                            return@registerListener
                        }

                        if (Server.clans().findByName(msg) != null)
                        {
                            player.sendMessage("&c已有同名公会存在，请换一个试试...")
                            return@registerListener
                        }

                        player.sendMessage("&a创建成功，你现在是 &7[&r${Server.clans().create(msg, player).name}&7] &a的会长了")
                        player.openInventory(VisualClan(player))
                        ChatPendingActions.remove(player.uuid)
                    }
                    ChatAction.GUILD_BANK_DEPOSIT   -> TODO()
                    ChatAction.GUILD_BANK_WITHDRAW  -> TODO()
                    ChatAction.PARTY_CODE ->
                    {

                    }
                }
            }
            else
            {
                event.setChatFormat(chatFormat)
            }
        }

        Minestom.registerListener(PlayerDisconnectEvent::class.java) { event -> ChatPendingActions.remove(event.player.uuid) }
    }

    enum class ChatAction
    {
        GUILD_PENDING_CREATE,
        GUILD_BANK_DEPOSIT,
        GUILD_BANK_WITHDRAW,
        PARTY_CODE,
        ;
    }

    enum class ConfirmAction
    {
        GUILD_KICK_MEMBER,
        GUILD_DISSOLVE;
    }
}