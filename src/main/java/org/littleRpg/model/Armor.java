package org.littleRpg.model;

public class Armor extends Item
{
    public int damageReduction = 0;

    public Armor(String name,String description, double weight, int damageReduction){
        super(name, description, weight);
        this.damageReduction = damageReduction;
    }

}
