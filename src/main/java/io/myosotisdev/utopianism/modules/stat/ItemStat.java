package io.myosotisdev.utopianism.modules.stat;

import io.myosotisdev.utopianism.battle.DamageMeta;
import io.myosotisdev.utopianism.modules.stat.data.ItemStatData;
import io.myosotisdev.utopianism.modules.stat.type.BooleanStat;
import io.myosotisdev.utopianism.modules.stat.type.DoubleStat;
import io.myosotisdev.utopianism.modules.stat.type.IntegerStat;
import io.myosotisdev.utopianism.modules.stat.type.StringStat;
import net.minestom.server.entity.EntityProjectile;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;

public abstract class ItemStat<T>
{
    public static final ItemStat ITEM_ID = new StringStat("id");
    public static final ItemStat ITEM_SLOT = new EquipSlot();
    public static final ItemStat DISABLE_INTERACTION = new BooleanStat("disable-interaction");
    public static final ItemStat DISABLE_ARROW_SHOOTING = new BooleanStat("disable-arrow-shooting");
    public static final ItemStat REQUIRED_CLASS_LEVEL = new RequiredLevel();
    public static final ItemStat REQUIRED_CAREER_LEVEL = new RequiredLevel();
    public static final ItemStat ATTACK_DAMAGE = new AttackDamage();
    public static final ItemStat ATTACK_SPEED = new AttackSpeed();
    public static final ItemStat ATTACK_RANGE = new AttackRange();
    public static final ItemStat CRITICAL_STRIKE_CHANCE = new DoubleStat("critical-strike-chance");
    public static final ItemStat CRITICAL_STRIKE_POWER = new DoubleStat("critical-strike-power");
    public static final ItemStat BLOCK_CHANCE = new DoubleStat("block-chance");
    public static final ItemStat BLOCK_POWER = new DoubleStat("block-power");
    public static final ItemStat DODGE_CHANCE = new DoubleStat("dodge-chance");
    public static final ItemStat DODGE_POWER = new DoubleStat("dodge-power");
    public static final ItemStat MAX_HEALTH = new DoubleStat("max-health");
    public static final ItemStat MAX_MANA = new DoubleStat("max-mana");
    public static final ItemStat HEALTH_REGEN = new HealthRegeneration();
    public static final ItemStat MANA_REGEN = new ManaRegeneration();
    public static final ItemStat ARROW_VELOCITY = new ArrowVelocity();
    public static final ItemStat DAMAGE_RESIST = new DoubleStat("damage-resist");
    public static final ItemStat DAMAGE_RESIST_RATE = new DoubleStat("damage-resist-%");
    public static final ItemStat MAGIC_RESIST = new DoubleStat("magic-resist");
    public static final ItemStat MAGIC_RESIST_RATE = new DoubleStat("magic-resist-%");
    public static final ItemStat UNBREAKABLE = new Unbreakable();
    public static final ItemStat FACTION_DAMAGE = new FactionDamage();
    public static final ItemStat KNOCKBACK = new DoubleStat("knockback");
    public static final ItemStat KNOCKBACK_RESIST = new DoubleStat("knockback-resist");
    public static final ItemStat TWO_HANDED = new BooleanStat("two-handed");
    public static final ItemStat SOULBOUND = new BooleanStat("soulbound");
    public static final ItemStat SOULBOUND_CHANCE = new DoubleStat("soulbound-chance");
    public static final ItemStat REINFORCE = new IntegerStat("reinforce");

    private String key;
    private Tag<T> tag;

    public ItemStat(String key, Tag<T> newTag)
    {
        this.key = key;
        this.tag = newTag;
    }

    public String getKey()
    {
        return key;
    }

    public Tag<T> getTag()
    {
        return tag;
    }

    /**
     * This function is called when generating new item stack
     * and it should apply attributes into the item
     *
     * @param itemStack
     * @param data
     */
    public abstract void onApply(ItemStack itemStack, ItemStatData<T> data);

    public abstract ItemStatData<T> defaultStatData();

    public T getLoadedData(ItemStack itemStack)
    {
        return itemStack.getTag(tag);
    }
}
