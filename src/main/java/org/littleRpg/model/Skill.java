package org.littleRpg.model;

public class Skill extends GameEntity {

    public PlayerClasses adventurerClass;
    public int value;
    public int attack;
    public int power;
    public Attribute buffAttribute;
    public int activationLength;
    public boolean isAttackRange = false;
    public boolean isAttackAll = false;
    public boolean isBuff = false;
    public boolean attackSkill = false;


    public Skill(String name, PlayerClasses adventurerClass, int value, int attack, int power, Attribute buffAttribute, int activationLength, boolean isAttackRange, boolean isAttackAll, boolean isBuff, boolean attackSkill) {
        super(name, name);
        this.adventurerClass = adventurerClass;
        this.value = value;
        this.attack = attack;
        this.power = power;
        this.buffAttribute = buffAttribute;
        this.activationLength = activationLength;
        this.isAttackRange = isAttackRange;
        this.isAttackAll = isAttackAll;
        this.isBuff = isBuff;
        this.attackSkill = attackSkill;
    }

}
