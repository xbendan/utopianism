package io.myosotisdev.minestom.util

object Texts
{
    val keyLangMapping: HashMap<String, String> = HashMap()

    fun get(key: String): String
    {
        return keyLangMapping[key] ?: "NO-MATCHED-VALUE"
    }
}