package org.littleRpg.model;

import java.io.Serializable;
import java.util.List;

public class Skill extends GameEntity implements Serializable {

    public List<PlayerClasses> adventurerClasses;
    public int attack;
    public int power;
    public int manaCost;
    public Attribute buffAttribute;
    public int activationLength;
    public boolean isRanged = false;
    public boolean isArea = false;
    public SkillType type;
    public Effect effect;


    public Skill(String name, List<PlayerClasses> adventurerClasses, Effect effect, int attack, int power, int manaCost, boolean isRanged, boolean isArea, SkillType type) {
        super(name, name);
        this.adventurerClasses = adventurerClasses;
        this.attack = attack;
        this.power = power;
        this.manaCost = manaCost;
        this.effect = effect;
        this.isRanged = isRanged;
        this.isArea = isArea;
        this.type = type;
    }

}
