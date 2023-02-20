package org.littleRpg.model;

public class Skill extends GameEntity {


    public int value;
    public int attack;
    public int power;
    public boolean isAttackRange = false;
    public boolean isAttackAll = false;

    public Skill(String name, int value, int attack, int power, boolean isAttackRange, boolean isAttackAll) {
        super(name, name);
        this.value = value;
        this.attack = attack;
        this.power = power;
        this.isAttackRange = isAttackRange;
        this.isAttackAll = isAttackAll;
    }
}
