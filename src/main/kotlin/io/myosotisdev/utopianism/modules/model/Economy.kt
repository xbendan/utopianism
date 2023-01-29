package io.myosotisdev.utopianism.modules.model

import io.myosotisdev.utopianism.modules.player.PlayerEx
import io.myosotisdev.utopianism.modules.player.money
import net.minestom.server.entity.Player

class Economy
{
    var money: Double

    constructor()
    {
        money = 0.0
    }

    constructor(money: Double)
    {
        this.money = money
    }

    fun giveMoney(amount: Double)
    {
        money += amount
    }

    fun takeMoney(amount: Double): Boolean
    {
        if (money < amount)
        {
            return false
        }
        money -= amount
        return true
    }

    fun deposit(amount: Double)
    {
        money += amount
    }

    fun withdraw(amount: Double): Boolean
    {
        if (amount > money)
        {
            return false
        }
        money -= amount
        return true
    }
}