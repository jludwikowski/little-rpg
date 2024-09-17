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

    public Skill(SkillBuilder builder) {
        super(builder.name, builder.description);
        this.adventurerClasses = adventurerClasses;
        this.attack = attack;
        this.power = power;
        this.manaCost = manaCost;
        this.effect = effect;
        this.isRanged = isRanged;
        this.isArea = isArea;
        this.type = type;
    }


        public static class SkillBuilder {

            private String name;
            private String description;
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

            public SkillBuilder(String name, String description) {
                this.name = name;
                this.description = description;
            }

            public SkillBuilder setAdventurerClasses(List<PlayerClasses> adventurerClasses) {
                this.adventurerClasses = adventurerClasses;
                return this;
            }

            public SkillBuilder setAttack(int attack) {
                this.attack = attack;
                return this;
            }

            public SkillBuilder setPower(int power) {
                this.power = power;
                return this;
            }

            public SkillBuilder setManaCost(int manaCost) {
                this.manaCost = manaCost;
                return this;
            }

            public SkillBuilder setBuffAttribute(Attribute buffAttribute) {
                this.buffAttribute = buffAttribute;
                return this;
            }

            public SkillBuilder setActivationLength(int activationLength) {
                this.activationLength = activationLength;
                return this;
            }

            public SkillBuilder setRanged(boolean ranged) {
                isRanged = ranged;
                return this;
            }

            public SkillBuilder setArea(boolean area) {
                isArea = area;
                return this;
            }

            public SkillBuilder setType(SkillType type) {
                this.type = type;
                return this;
            }

            public SkillBuilder setEffect(Effect effect) {
                this.effect = effect;
                return this;
            }

            public Skill build() {
                return new Skill(this);
            }
        }

}
