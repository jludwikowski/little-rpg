package org.littleRpg.model;

public class Archetype {
    public float maxHp;
    public float maxMana;
    public float attack;
    public float armor;
    public float strength;
    public Archetype(float maxHp, float maxMana, float attack, float armor, float strength) {
        this.maxHp = maxHp;
        this.maxMana = maxMana;
        this.attack = attack;
        this.armor = armor;
        this.strength = strength;
    }


}
