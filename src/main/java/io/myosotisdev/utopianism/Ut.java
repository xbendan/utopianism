package io.myosotisdev.utopianism;

import io.myosotisdev.minestom.Minestom;
import io.myosotisdev.utopianism.modules.*;
import io.myosotisdev.utopianism.util.Namespaces;

public class Ut
{
    public static String ModuleWarzone = Namespaces.Utopianism + "-" + "warzone";
    public static String ModuleItem = Namespaces.Utopianism + "-" + "item";
    public static String ModuleGuild = Namespaces.Utopianism + "-" + "guild";
    public static String ModuleEntityData = Namespaces.Utopianism + "-" + "entity_data";
    public static String ModuleCraft = Namespaces.Utopianism + "-" + "craft";

    public static EntityDataModule entitydata()
    {
        return (EntityDataModule) Minestom.getModule(Ut.ModuleEntityData);
    }

    public static GuildModule guild()
    {
        return (GuildModule) Minestom.getModule(Ut.ModuleGuild);
    }

    public static ItemModule item()
    {
        return (ItemModule) Minestom.getModule(Ut.ModuleItem);
    }

    public static DungeonModule warzone()
    {
        return (DungeonModule) Minestom.getModule(Ut.ModuleWarzone);
    }

    public static CraftingModule crafting()
    {
        return (CraftingModule) Minestom.getModule(Ut.ModuleCraft);
    }
}
