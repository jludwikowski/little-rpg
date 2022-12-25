package org.littleRpg.model;

public class Weapon extends Item {

    public int bonusAttack = 0;
    public int baseDamageValue = 0;
    public boolean isRanged = false;


    public Weapon(String name,String description, double weight, int bonusAttack, int baseDamageValue, boolean isRanged) {
        super(name, ItemTypes.weapon,  description, weight);
        this.bonusAttack = bonusAttack;
        this.baseDamageValue = baseDamageValue;
        this.isRanged = isRanged;
    }

}
