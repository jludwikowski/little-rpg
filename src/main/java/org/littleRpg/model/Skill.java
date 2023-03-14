package org.littleRpg.model;

public class Skill extends GameEntity {


    public int value;
    public int attack;
    public int power;
    public int damageReduction;
    public int activationLength;
    public boolean isAttackRange = false;
    public boolean isAttackAll = false;
    public boolean isDeffendUp = false;
    public boolean attackSkill = false;
    public boolean deffendSkill = false;

    public Skill(String name, int value, int attack, int power, int damageReduction, int activationLength, boolean isAttackRange, boolean isAttackAll, boolean isDeffendUp, boolean attackSkill, boolean deffendSkill) {
        super(name, name);
        this.value = value;
        this.attack = attack;
        this.power = power;
        this.damageReduction = damageReduction;
        this.activationLength = activationLength;
        this.isAttackRange = isAttackRange;
        this.isAttackAll = isAttackAll;
        this.isDeffendUp = isDeffendUp;
        this.attackSkill = attackSkill;
        this.deffendSkill = deffendSkill;
    }
}
