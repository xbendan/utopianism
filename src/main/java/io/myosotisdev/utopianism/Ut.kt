package io.myosotisdev.utopianism

import io.myosotisdev.minestom.Minestom
import io.myosotisdev.utopianism.modules.*
import io.myosotisdev.utopianism.util.Namespaces

object Ut
{
    const val ModuleAchievement = Namespaces.Utopianism + "-" + "achievement"
    const val ModuleAuction = Namespaces.Utopianism + "-" + "auction"
    const val ModuleBattlePass = Namespaces.Utopianism + "-" + "battlepass"
    const val ModuleChat = Namespaces.Utopianism + "-" + "chat"
    const val ModuleCitizen = Namespaces.Utopianism + "-" + "citizen"
    const val ModuleCraft = Namespaces.Utopianism + "-" + "craft"
    const val ModuleCrates = Namespaces.Utopianism + "-" + "crates"
    const val ModuleDungeon = Namespaces.Utopianism + "-" + "dungeon"
    const val ModuleEntityData = Namespaces.Utopianism + "-" + "entitydata"
    const val ModuleGift = Namespaces.Utopianism + "-" + "gift"
    const val ModuleGuild = Namespaces.Utopianism + "-" + "guild"
    const val ModuleHomeland = Namespaces.Utopianism + "-" + "homeland"
    const val ModuleItem = Namespaces.Utopianism + "-" + "item"
    const val ModuleMailbox = Namespaces.Utopianism + "-" + "mailbox"
    const val ModuleMarriage = Namespaces.Utopianism + "-" + "marriage"
    const val ModuleMechanize = Namespaces.Utopianism + "-" + "mechanize"
    const val ModuleParty = Namespaces.Utopianism + "-" + "party"
    const val ModuleRegion = Namespaces.Utopianism + "-" + "region"
    const val ModuleTask = Namespaces.Utopianism + "-" + "task"
    const val ModuleTrade = Namespaces.Utopianism + "-" + "trade"
    fun entitydata(): EntityDataModule
    {
        return Minestom.getModule(ModuleEntityData) as EntityDataModule
    }

    fun guild(): GuildModule
    {
        return Minestom.getModule(ModuleGuild) as GuildModule
    }

    fun item(): ItemModule
    {
        return Minestom.getModule(ModuleItem) as ItemModule
    }

    fun warzone(): DungeonModule
    {
        return Minestom.getModule(ModuleDungeon) as DungeonModule
    }

    fun crafting(): CraftingModule
    {
        return Minestom.getModule(ModuleCraft) as CraftingModule
    }

    fun party(): PartyModule
    {
        return Minestom.getModule(ModuleParty) as PartyModule
    }
}