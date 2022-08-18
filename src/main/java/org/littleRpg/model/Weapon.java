package org.littleRpg.model;

public class Weapon extends Item {

    public int bonusAttack = 0;
    public int baseDamageValue = 0;

    public Weapon(String name,String description, double weight, int bonusAttack, int baseDamageValue) {
        super(name, description, weight);
        this.bonusAttack = bonusAttack;
        this.baseDamageValue = baseDamageValue;
    }

}
