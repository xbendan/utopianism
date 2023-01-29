package io.myosotisdev.utopianism.modules.stat

import com.google.gson.JsonObject
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData
import io.myosotisdev.utopianism.modules.stat.type.BooleanStat
import io.myosotisdev.utopianism.modules.stat.type.DoubleStat
import io.myosotisdev.utopianism.modules.stat.type.IntegerStat
import io.myosotisdev.utopianism.modules.stat.type.StringStat
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

abstract class ItemStat<T>(val key: String, val tag: Tag<T>)
{

    init { stats[key] = this }

    /**
     * This function is called when generating new item stack
     * and it should apply attributes into the item
     *
     * @param itemStack
     * @param data
     */
    open fun onApply(itemStack: ItemStack, data: ItemStatData<out T>)
    {
        itemStack.withTag(tag, data.value)
    }

    abstract fun defaultStatData(): ItemStatData<T>?

    abstract fun createStatData(jsonObject: JsonObject): ItemStatData<T>

    open fun getLoadedData(itemStack: ItemStack): T
    {
        return itemStack.getTag(tag)
    }

    companion object
    {
        private val stats: HashMap<String, ItemStat<*>> = HashMap()

        val ITEM_ID: ItemStat<*> = StringStat("id")
        val ITEM_SLOT: ItemStat<*> = EquipSlot()
        val DISABLE_INTERACTION: ItemStat<*> = BooleanStat("disable-interaction")
        val DISABLE_ARROW_SHOOTING: ItemStat<*> = BooleanStat("disable-arrow-shooting")
        val REQUIRED_CLASS_LEVEL: ItemStat<*> = RequiredLevel()
        val REQUIRED_CAREER_LEVEL: ItemStat<*> = RequiredLevel()
        val ATTACK_DAMAGE: ItemStat<*> = AttackDamage()
        val ATTACK_SPEED: ItemStat<*> = AttackSpeed()
        val ATTACK_RANGE: ItemStat<*> = AttackRange()
        val CRITICAL_STRIKE_CHANCE: ItemStat<*> = DoubleStat("critical-strike-chance")
        val CRITICAL_STRIKE_POWER: ItemStat<*> = DoubleStat("critical-strike-power")
        val BLOCK_CHANCE: ItemStat<*> = DoubleStat("block-chance")
        val BLOCK_POWER: ItemStat<*> = DoubleStat("block-power")
        val DODGE_CHANCE: ItemStat<*> = DoubleStat("dodge-chance")
        val DODGE_POWER: ItemStat<*> = DoubleStat("dodge-power")
        val MAX_HEALTH: ItemStat<*> = DoubleStat("max-health")
        val MAX_MANA: ItemStat<*> = DoubleStat("max-mana")
        val HEALTH_REGEN: ItemStat<*> = HealthRegeneration()
        val MANA_REGEN: ItemStat<*> = ManaRegeneration()
        val ARROW_VELOCITY: ItemStat<*> = ArrowVelocity()
        val DAMAGE_RESIST: ItemStat<*> = DoubleStat("damage-resist")
        val DAMAGE_RESIST_RATE: ItemStat<*> = DoubleStat("damage-resist-%")
        val MAGIC_RESIST: ItemStat<*> = DoubleStat("magic-resist")
        val MAGIC_RESIST_RATE: ItemStat<*> = DoubleStat("magic-resist-%")
        val UNBREAKABLE: ItemStat<*> = Unbreakable()
        val FACTION_DAMAGE: ItemStat<*> = FactionDamage()
        val KNOCKBACK: ItemStat<*> = DoubleStat("knockback")
        val KNOCKBACK_RESIST: ItemStat<*> = DoubleStat("knockback-resist")
        val TWO_HANDED: ItemStat<*> = BooleanStat("two-handed")
        val SOULBOUND: ItemStat<*> = BooleanStat("soulbound")
        val SOULBOUND_CHANCE: ItemStat<*> = DoubleStat("soulbound-chance")
        val REINFORCE: ItemStat<*> = IntegerStat("reinforce")

        fun getByName(key: String): ItemStat<*>? = stats[key]
    }
}