package org.littleRpg.model;

public class Archetype {
    public int maxHp;
    public int maxMana;
    public int attack;
    public int armor;
    public int strength;
    public Archetype(int maxHp, int maxMana, int attack, int armor, int strength) {
        this.maxHp = maxHp;
        this.maxMana = maxMana;
        this.attack = attack;
        this.armor = armor;
        this.strength = strength;
    }


}
