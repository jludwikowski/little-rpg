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
}
