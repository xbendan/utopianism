package io.myosotisdev.minestom

import com.google.gson.JsonElement

interface IJsonData
{
    fun asJsonCopy(): JsonElement
}