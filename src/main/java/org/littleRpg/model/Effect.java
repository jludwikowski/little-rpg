package org.littleRpg.model;

import java.io.Serializable;
import java.util.List;

public class Effect extends GameEntity implements Serializable {

    public int value;
    public int attack;
    public int power;
    public boolean attackBuff = false;
    public boolean defendBuff = false;
    public EffectType type;
    public Attribute buffAttribute;


    public Effect(String name, int value, int attack, int power, boolean attackBuff, boolean defendBuff, EffectType type, Attribute buffAttribute) {
        super(name, name);
        this.value = value;
        this.attack = attack;
        this.power = power;
        this.type = type;
        this.attackBuff = false;
        this.defendBuff = false;
        this.buffAttribute = buffAttribute;


    }
}
