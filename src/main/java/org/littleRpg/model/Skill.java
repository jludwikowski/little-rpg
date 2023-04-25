package org.littleRpg.model;

import java.util.List;

public class Skill extends GameEntity {

    public List<PlayerClasses> adventurerClasses;
    public int value;
    public int attack;
    public int power;
    public int manaCost;
    public Attribute buffAttribute;
    public int activationLength;
    public boolean isRanged = false;
    public boolean isArea = false;
    public SkillType type;


    public Skill(String name, List<PlayerClasses> adventurerClasses, int value, int attack, int power, int manaCost, Attribute buffAttribute, int activationLength, boolean isRanged, boolean isArea, SkillType type) {
        super(name, name);
        this.adventurerClasses = adventurerClasses;
        this.value = value;
        this.attack = attack;
        this.power = power;
        this.manaCost = manaCost;
        this.buffAttribute = buffAttribute;
        this.activationLength = activationLength;
        this.isRanged = isRanged;
        this.isArea = isArea;
        this.type = type;

    }

}
