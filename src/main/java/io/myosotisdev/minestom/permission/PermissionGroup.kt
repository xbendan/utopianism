package io.myosotisdev.minestom.permission

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.collections.HashSet

class PermissionGroup : IPermissionHandler
{
    private val permissions: MutableSet<Permission?> = HashSet()
    val id: String;
    var name: String = ""

    constructor(id: String)
    {
        this.id = id
        this.name = id;
    }

    constructor(id: String, file: File) : this(id)
    {
        var jsonObject: JsonObject = JsonParser.parseReader(FileReader(file)).asJsonObject

    }

    override fun addPerm(perm: Permission?)
    {
        permissions.add(perm)
    }

    override fun removePerm(perm: Permission?)
    {
        permissions.remove(perm)
    }

    override fun hasPerm(perm: Permission?): Boolean
    {
        return permissions.stream()
                .anyMatch { permission: Permission? -> permission!!.matchWith(perm!!) }
    }

    override fun permissions(): Collection<Permission?>?
    {
        return permissions
    }
}