package io.myosotisdev.utopianism.modules.guild;

import io.myosotisdev.utopianism.modules.region.IRegionKeeper;
import io.myosotisdev.utopianism.modules.region.Region;
import io.myosotisdev.utopianism.modules.player.ImplPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Guild implements IRegionKeeper
{
    private String       name;
    private List<String> manifest;
    private String       inventoryIconTextures;
    private UUID uid;
    private HashMap<UUID, ImplPlayer> mappedPlayers;

    private Set<Region> regions;
    private int level;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getManifest()
    {
        return manifest;
    }

    public void setManifest(List<String> manifest)
    {
        this.manifest = manifest;
    }

    public String getInventoryIconTextures()
    {
        return inventoryIconTextures;
    }

    public void setInventoryIconTextures(String inventoryIconTextures)
    {
        this.inventoryIconTextures = inventoryIconTextures;
    }

    public HashMap<UUID, ImplPlayer> getPlayers()
    {
        return mappedPlayers;
    }

    public Set<Region> getRegions()
    {
        return regions;
    }

    public UUID getUid()
    {
        return uid;
    }

    public void setUid(UUID uid)
    {
        this.uid = uid;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
