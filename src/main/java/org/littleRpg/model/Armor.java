package org.littleRpg.model;

public class Armor extends Item
{
    public int damageReduction = 0;

    public Armor(String name, String description, double weight, int damageReduction, WearSlot slot){
        super(name, ItemTypes.armor, description, weight, null, slot);
        this.damageReduction = damageReduction;
    }

}
