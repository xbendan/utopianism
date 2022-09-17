package io.myosotisdev.utopianism.modules.expert

abstract class SkillExpert
{
    companion object
    {
        val MINING = MiningExpert()
        val LOGGING = LoggingExpert()
        val ALCHEMY = AlchemyExpert()
        val FARMER = FarmerExpert()
        val PHARMACY = PharmacyExpert()
    }
}