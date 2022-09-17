package io.myosotisdev.minestom.permission

interface IPermissionHandler
{
    fun addPerm(perm: Permission?)
    fun removePerm(perm: Permission?)
    fun hasPerm(perm: Permission?): Boolean
    fun permissions(): Collection<Permission?>?
}