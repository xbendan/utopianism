package io.myosotisdev.utopianism

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.minestom.command.*
import io.myosotisdev.minestom.module.LocaleModule
import io.myosotisdev.minestom.module.PermissionModule
import io.myosotisdev.utopianism.command.EconomyCommand
import io.myosotisdev.utopianism.command.MenuCommand
import io.myosotisdev.utopianism.command.TestCommand
import io.myosotisdev.utopianism.listeners.InteractivityEvents
import io.myosotisdev.utopianism.listeners.InventoryEvents
import io.myosotisdev.utopianism.listeners.PlayerEvents
import io.myosotisdev.utopianism.manager.BattleManager
import io.myosotisdev.utopianism.modules.*
import io.myosotisdev.utopianism.modules.entitydata.EntityData
import io.myosotisdev.utopianism.modules.entitydata.PlayerData
import io.myosotisdev.utopianism.modules.model.Economy
import io.myosotisdev.utopianism.modules.player.PlayerEx
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player
import net.minestom.server.utils.mojang.MojangUtils
import java.util.*

class Server
{
    val inventoryEvents: InventoryEvents = InventoryEvents()
    val interactivityEvents: InteractivityEvents = InteractivityEvents(this)
    val playerEvents: PlayerEvents = PlayerEvents()

    val clanManager = ClanManager()
    val chatManager = ChatManager()
    val homelandManager = HomelandManager()
    val vaultManager = VaultManager()
    val teamManager = TeamManager()
    val entityManager = EntityDataManager()
    val battleManager = BattleManager()

    init
    {
        Minestom.connectionManager.setUuidProvider { _, username -> UUID.fromString(MojangUtils.fromUsername(username)!!["id"].asString) }
        Minestom.connectionManager.setPlayerProvider { uuid, username, connection -> PlayerEx(uuid, username, connection) }
        Minestom.registerCommands(
                AdminCommand(),
                KillCommand(),
                GameModeCommand(),
                ManageCommand(),
                PermissionCommand(),
                TestCommand(),
                EconomyCommand(),
                MenuCommand()
        )
        Minestom.registerModules(
                LocaleModule(),
                PermissionModule(),

                BattlePassModule(),
                battleManager,
                chatManager,
                CraftingModule(),
                entityManager,
                clanManager,
                homelandManager,
                vaultManager,
                ItemManager(),
                MailboxModule(),
                teamManager
        )

        srv = this
    }



    companion object
    {
        lateinit var srv: Server

        fun setCustomName(uuid: UUID, string: String)
        {
            val name = srv.chatManager.loadPlayer(uuid)
            if (name != null)
                name.customName = string
        }

        fun getCustomName(player: Player): String = getCustomName(player.uuid)

        fun getCustomName(uuid: UUID): String = srv.chatManager.getCustomName(uuid)

        fun getDisplayName(player: Player): String = getDisplayName(player.uuid)

        fun getDisplayName(uuid: UUID): String = srv.chatManager.getDisplayName(uuid)

        fun getEconomy(uuid: UUID): Economy? = srv.vaultManager.getEconomy(uuid)

        fun getChatManager(): ChatManager = srv.chatManager

        fun clans(): ClanManager = srv.clanManager

        fun homelands(): HomelandManager = srv.homelandManager

        fun vaults(): VaultManager = srv.vaultManager

        fun teams(): TeamManager = srv.teamManager

        fun getEntityData(entity: Entity): EntityData? = srv.entityManager[entity]

        fun getPlayerData(player: Player): PlayerData = srv.entityManager.getPlayerData(player.uuid)!!
    }
}