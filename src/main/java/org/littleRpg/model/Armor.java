package org.littleRpg.model;

import java.util.Arrays;
import java.util.List;

public class Armor extends Item
{
    public int damageReduction = 0;

    public Armor(String name, String description, double weight, int damageReduction, List<WearSlot> slots, int price, int itemLevel){
        super(name, ItemTypes.armor, description, weight, null, slots, price, itemLevel);
        this.damageReduction = damageReduction;
    }

    public void upgradeItemLevel(){
        this.damageReduction += (int) (this.damageReduction * 0.5);
        itemLevel ++ ;
    }

}
