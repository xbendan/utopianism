package io.myosotisdev.minestom.util

import java.util.*

object SkullUtils
{
    fun getEncoded(skinUrl: String): String {
        val var1: ByteArray = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinUrl).encodeToByteArray());
        return String(var1);
    }
}