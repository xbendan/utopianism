package io.myosotisdev.utopianism.modules.homeland

import io.myosotisdev.minestom.Identifiable
import io.myosotisdev.minestom.Nameable

interface IHomelandLord : Nameable, Identifiable
{
    var homeland: Homeland?

    fun recall()
}