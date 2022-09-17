package io.myosotisdev.utopianism.modules.region

import net.minestom.server.coordinate.Pos
import java.util.*
import kotlin.collections.HashMap

class Region protected constructor(val uid: UUID)
{
    var cornerAA: Pos? = null
    var cornerBB: Pos? = null
    var name: String? = null
    var respawnPoint: Pos? = null
    var owner: IRegionKeeper? = null
        private set
    private val defaultPermissions: Map<RegionPermission, Boolean> = HashMap()

    constructor(AA: Pos?, BB: Pos?, uid: UUID, name: String?, owner: IRegionKeeper?) : this(uid)
    {
        cornerAA = AA
        cornerBB = BB
        this.name = name
        this.owner = owner
    }

    fun setowner(owner: IRegionKeeper?)
    {
        this.owner = owner
    }

    fun getPermByDefault(rp: RegionPermission?): Boolean
    {
        return false
    }

    fun setPermByDefault(rp: RegionPermission?, `val`: Boolean)
    {
    }
}