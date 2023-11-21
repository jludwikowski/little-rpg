package org.littleRpg.model;

import java.util.Arrays;
import java.util.List;

public class Armor extends Item
{
    public int damageReduction = 0;

    public Armor(String name, String description, double weight, int damageReduction, List<WearSlot> slots){
        super(name, ItemTypes.armor, description, weight, null, slots);
        this.damageReduction = damageReduction;
    }

}
