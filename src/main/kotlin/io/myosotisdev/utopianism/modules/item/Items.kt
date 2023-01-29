package io.myosotisdev.utopianism.modules.item

import io.myosotisdev.utopianism.modules.ItemManager

object Items
{
    lateinit var Instance: ItemManager

    fun getByName(name: String): ItemStackTemplate? = Instance.getByName(name)
}