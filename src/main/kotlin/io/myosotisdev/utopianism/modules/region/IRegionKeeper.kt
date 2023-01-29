package io.myosotisdev.utopianism.modules.region

interface IRegionKeeper
{
    val regions: Collection<Region?>
    fun acquireRegion(region: Region?)
    fun releaseRegion(region: Region?)
}