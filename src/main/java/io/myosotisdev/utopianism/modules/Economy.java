package io.myosotisdev.utopianism.modules;

public class Economy
{
    private double money;

    public Economy()
    {
        this.money = 0;
    }

    public Economy(double money)
    {
        this.money = money;
    }

    public void giveMoney(double amount)
    {
        this.money += amount;
    }

    public void setMoney(double amount)
    {
        this.money = amount;
    }

    public boolean takeMoney(double amount)
    {
        if(this.money < amount)
        {
            return false;
        }
        this.money -= amount;

        return true;
    }

    public double getMoney()
    {
        return this.money;
    }

    public void deposit(double amount)
    {
        this.money += amount;
    }

    public boolean withdraw(double amount)
    {
        if(amount > this.money)
        {
            return false;
        }

        this.money -= amount;
        return true;
    }
}
