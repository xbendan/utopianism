package io.myosotisdev.utopianism.modules.region;

import net.minestom.server.coordinate.Pos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Region
{
    private final UUID uid;
    private Pos cornerAA;
    private Pos cornerBB;
    private String name;
    private Pos respawnPoint;
    private IRegionKeeper owner;
    private Map<RegionPermission, Boolean> defaultPermissions;

    protected Region(UUID uid)
    {
        this.uid = uid;
        this.defaultPermissions = new HashMap<>();
    }

    public Region(Pos AA, Pos BB, UUID uid, String name, IRegionKeeper owner)
    {
        this(uid);

        this.cornerAA = AA;
        this.cornerBB = BB;
        this.name = name;
        this.owner = owner;
    }
    
    public UUID getUid() { return this.uid; }

    public Pos getCornerAA() { return this.cornerAA; }

    public void setCornerAA(Pos val) { this.cornerAA = val; }

    public Pos getCornerBB() { return this.cornerBB; }

    public void setCornerBB(Pos val) { this.cornerBB = val; }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Pos getRespawnPoint()
    {
        return respawnPoint;
    }

    public void setRespawnPoint(Pos respawnPoint)
    {
        this.respawnPoint = respawnPoint;
    }

    public IRegionKeeper getOwner()
    {
        return owner;
    }

    public void setowner(IRegionKeeper owner)
    {
        this.owner = owner;
    }

    public boolean getPermByDefault(RegionPermission rp)
    {
        return false;
    }

    public void setPermByDefault(RegionPermission rp, boolean val)
    {

    }
}
