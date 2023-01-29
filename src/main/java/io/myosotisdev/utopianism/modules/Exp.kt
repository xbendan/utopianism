package io.myosotisdev.utopianism.modules

import kotlin.math.roundToInt

class Exp(val calc: java.util.function.Function<Int, Int>)
{
    var level = 1
    var exp = 0.0
    val required
        get() = calc.apply(level)

    constructor(totalExp: Double, calc: java.util.function.Function<Int, Int>) : this(calc)
    {

    }

    constructor(level: Int, exp: Double, calc: java.util.function.Function<Int, Int>): this(calc)
    {
        this.level = level
        this.exp = exp
    }

    fun reduceExp(amount: Int)
    {
        while (amount > 0)
        {
            return
        }
    }

    fun earnExp(amount: Int)
    {
    }

    companion object
    {
        fun progressBar(exp: Exp, length: Int = 80): String
        {
            val filled = (length * (exp.exp / exp.required)).roundToInt()
            val blank = length - filled
            val builder = StringBuilder()

            var index: Int = 0

            builder.append("&a")
            while (index < filled)
            {
                builder.append('|')
                index++
            }
            builder.append("&7")
            while (index < length)
            {
                builder.append('|')
                index++
            }

            return builder.toString()
        }
    }
}