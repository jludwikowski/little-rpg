package org.littleRpg.model;

import java.util.List;

public class Human extends Monster{

    public int gamerId;
    public int location;
    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot) {
        super(name, description,maxHp, currentHp, attack, strength, mainWeapon, armor, loot);
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }

}
