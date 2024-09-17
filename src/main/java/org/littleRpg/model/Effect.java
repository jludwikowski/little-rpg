package org.littleRpg.model;

import java.io.Serializable;
import java.util.List;

public class Effect extends GameEntity implements Serializable {

    public int power;
    public boolean attackBuff;
    public boolean defendBuff;
    public EffectType type;
    public Attribute buffAttribute;
    public int activationLength;


    public Effect(String name, int power, EffectType type, Attribute buffAttribute, int activationLength) {
        super(name, name);
        this.power = power;
        this.type = type;
        this.attackBuff = false;
        this.defendBuff = false;
        this.buffAttribute = buffAttribute;
        this.activationLength = activationLength;

    }

    public Effect(EffectBuilder builder) {
        super(builder.name, builder.description);
        this.power = power;
        this.type = type;
        this.attackBuff = attackBuff;
        this.defendBuff = defendBuff;
        this.buffAttribute = buffAttribute;
        this.activationLength = activationLength;

    }

    public static class EffectBuilder{
        private String name;
        private String description;
        private int power = 1;
        private boolean attackBuff = false;
        private boolean defendBuff = false;
        private EffectType type;
        private Attribute buffAttribute;
        private int activationLength = 1;

        public EffectBuilder(String name, String description){
            this.name = name;
            this.description = description;
        }



        public EffectBuilder setPower(int power) {
            this.power = power;
            return this;
        }

        public EffectBuilder setAttackBuff(boolean attackBuff) {
            this.attackBuff = attackBuff;
            return this;
        }

        public EffectBuilder setDefendBuff(boolean defendBuff) {
            this.defendBuff = defendBuff;
            return this;
        }

        public EffectBuilder setType(EffectType type) {
            this.type = type;
            return this;
        }

        public EffectBuilder setBuffAttribute(Attribute buffAttribute) {
            this.buffAttribute = buffAttribute;
            return this;
        }

        public EffectBuilder setActivationLength(int activationLength) {
            this.activationLength = activationLength;
            return this;
        }

        public Effect build(){
            return new Effect(this);
        }
    }
}
