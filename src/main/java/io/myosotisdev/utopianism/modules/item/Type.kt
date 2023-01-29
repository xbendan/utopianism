package io.myosotisdev.utopianism.modules.item

import io.myosotisdev.utopianism.battle.AttackTargetType

enum class Type(val selector: AttackTargetType, vararg defaultArguments: Any?)
{
    SWORD(AttackTargetType.SECTOR, 4.75, 65.0),
    DAGGER(AttackTargetType.ONE, 3.5),
    SPEAR(AttackTargetType.LINE, 5.5),
    HAMMER(AttackTargetType.RECTANGLE, 4.2),
    GAUNTLET(AttackTargetType.ONE, 3.2),
    STAFF(AttackTargetType.SPHERE, 2.0),
    BOW(AttackTargetType.ONE),
    CROSSBOW(AttackTargetType.LINE, 1.5),
    LONGBOW(AttackTargetType.LINE, 3.5),
    ORNAMENT(AttackTargetType.NONE),
    ARMOR(AttackTargetType.NONE),
    TOOL(AttackTargetType.NONE),
    CONSUMABLE(AttackTargetType.NONE),
    MISCELLANEOUS(AttackTargetType.NONE),
    GEMSTONE(AttackTargetType.NONE),
    BLOCK(AttackTargetType.NONE),
    ITEM(AttackTargetType.NONE),
    SCROLL(AttackTargetType.NONE),
    SKILL_BOOK(AttackTargetType.NONE),
    BUILD_TOWER(AttackTargetType.NONE)
    ;
}