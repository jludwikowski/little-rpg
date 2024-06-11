package org.littleRpg.model;

import java.util.Arrays;
import java.util.List;

public class Weapon extends Item {

    public int bonusAttack = 0;
    public int baseDamageValue = 0;
    public boolean isRanged = false;
    public boolean bothHand = false;



    public Weapon(String name,String description, double weight, int bonusAttack, int baseDamageValue, boolean isRanged, boolean bothHand, List<WearSlot> slots, int price, int itemLevel) {
        super(name, ItemTypes.weapon,  description, weight, null, slots,price, itemLevel);
        this.bonusAttack = bonusAttack;
        this.baseDamageValue = baseDamageValue;
        this.isRanged = isRanged;
        this.bothHand = bothHand;

    }

    public void upgradeItemLevel(){
        this.bonusAttack += (int) (this.bonusAttack * 0.5);
        this.baseDamageValue += (int) (this.baseDamageValue * 0.5);
        itemLevel ++ ;
    }
}
