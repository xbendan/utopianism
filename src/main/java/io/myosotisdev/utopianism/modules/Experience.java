package io.myosotisdev.utopianism.modules;

public class Experience
{
    private int level;
    private int expToLvl;
    private int totalExp;

    public Experience(int totalExp)
    {
        this.totalExp = totalExp;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getExpToLvl()
    {
        return expToLvl;
    }

    public void setExpToLvl(int expToLvl)
    {
        this.expToLvl = expToLvl;
    }

    public void reduceExp(int amount)
    {
        this.totalExp -= amount;
    }

    public void earnExp(int amount)
    {

    }

    public int getTotalExp()
    {
        return totalExp;
    }

    public void setTotalExp(int totalExp)
    {
        this.totalExp = totalExp;
    }
}
