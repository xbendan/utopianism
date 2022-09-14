package io.myosotisdev.utopianism.modules;

public class Economy
{
    private int money;

    public Economy()
    {
        this.money = 0;
    }

    public Economy(int money)
    {
        this.money = money;
    }

    public void giveMoney(int amount)
    {
        this.money += amount;
    }

    public void setMoney(int amount)
    {
        this.money = amount;
    }

    public boolean takeMoney(int amount)
    {
        if(this.money < amount)
        {
            return false;
        }
        this.money -= amount;

        return true;
    }

    public int getMoney()
    {
        return this.money;
    }

    public void deposit(int amount)
    {
        this.money += amount;
    }

    public boolean withdraw(int amount)
    {
        if(amount > this.money)
        {
            return false;
        }

        this.money -= amount;
        return true;
    }
}
