package org.littleRpg.model;

import org.littleRpg.engine.ListHelper;

import java.io.Serializable;
import java.util.*;

public class Monster extends LivingEntity implements Serializable {

    public float maxHp = 1;
    public float currentHp = 1;
    public int maxMana = 1;
    public int currentMana = 1;
    public int attack = 0;
    public int strength = 0;
    public int monsterDamageReduction = 0;

    public MonsterTypes type;
    public List<Effect> activeEffects = new ArrayList<>();
    public int goldCoins;

    public Monster(MonsterTypes type, String name, String description, float maxHp, float currentHp, int maxMana, int currentMana, int attack, int strength, int monsterDamageReduction, Weapon mainWeapon, Map<WearSlot, Armor> mainArmor, List<Item> loot, List<Skill> skills, int goldCoins) {
        super(name, description, loot, mainWeapon, mainArmor);
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.maxMana = maxMana;
        this.currentMana = currentMana;
        this.attack = attack;
        this.strength = strength;
        this.monsterDamageReduction = monsterDamageReduction;
        this.goldCoins = goldCoins;
    }

    public String getStats() {
        int finalDamageReduction = monsterDamageReduction + getArmorValue();
        return "maxHp: " + String.valueOf(this.maxHp) + "\n" + "currentHP: " + String.valueOf(this.currentHp) + "\n" +
                "attack: " + String.valueOf(this.attack) + "\n" + "strength: " + String.valueOf(this.strength) + "\n" +
                "damageReduction: " + String.valueOf(finalDamageReduction);

    }

    public int getDamage() {
        if(this.equippedItems.get(WearSlot.mainHand) != null) {
            return ((Weapon) this.equippedItems.get(WearSlot.mainHand)).baseDamageValue + this.strength;
        }
        return this.strength;
    }



    public int getBaseAttribute(Attribute attribute){
        int buff = 0;
        ListIterator<Effect> iterator = activeEffects.listIterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            if (effect.type == EffectType.buff && attribute == effect.buffAttribute) {
                buff += effect.power;
                System.out.println(buff);
                System.out.println(effect.power);
            }
        }


        switch (attribute) {
            case monsterDamageReduction:
                int finalDamageReduction = monsterDamageReduction + getArmorValue();
                return finalDamageReduction + buff;
            case strength:
                return strength + buff;
            case maxHp:
                return (int) (maxHp + buff);
            case attack:
                if(this.equippedItems.get(WearSlot.mainHand) != null) {
                    return ((Weapon) this.equippedItems.get(WearSlot.mainHand)).bonusAttack + attack + buff;
                }
                return attack + buff;
        }
        return 0;
    }


    public int getAttribute(Attribute attribute){
        return getBaseAttribute(attribute);
    }

    public void damage(int damageValue) {
        int damageReduction = getAttribute(Attribute.monsterDamageReduction);
        int actuallDamageValue = damageValue - damageReduction < 0 ? 0 : damageValue - damageReduction;
        this.currentHp = this.currentHp - actuallDamageValue;
        if(this.currentHp <=0 ){
            System.out.println("DEAD");
        }
    }

    public void heal(int healValue) {
        this.currentHp = this.currentHp + healValue > getAttribute(Attribute.maxHp) ? getAttribute(Attribute.maxHp) : this.currentHp + healValue;
    }

    public void adjust(Monster adjust){
        this.name = adjust.name != null ? adjust.name + " " + this.name : this.name;
        this.description = adjust.description + " " + this.description;
        this.currentHp *= adjust.currentHp;
        this.maxHp *= adjust.maxHp;
        this.attack += adjust.attack;
        this.strength += adjust.strength;
        this.monsterDamageReduction += adjust.monsterDamageReduction;
        for (Map.Entry<WearSlot,Item> entry : equippedItems.entrySet()){
            Item equippedItem = adjust.equippedItems.get(entry.getKey()) != null ?
                    adjust.equippedItems.get(entry.getKey()) : entry.getValue();
            equippedItems.replace(entry.getKey(), equippedItem);
        }
    }

    public void effectTurnCounter () {
        if (activeEffects != null){
            ListHelper.showList("Active Effects: ",activeEffects,false);
            ListIterator <Effect> iterator = activeEffects.listIterator();
            while(iterator.hasNext()){
                Effect effect = iterator.next();
                if(effect.type == EffectType.heal){
                    this.heal(effect.power);
                    System.out.println(this.getName() + " is healed for " + effect.power);
                }
                effect.activationLength -= 1;
                System.out.println("skill kończy się za: " + effect.activationLength);
                if (effect.activationLength == 0){
                    iterator.remove();
                }
            }
        }
    }



    public String getDescription() {
        String monsterDescription = description +
                ((this.equippedItems.get(WearSlot.mainHand) != null) ? "\n armed with " + (this.equippedItems.get(WearSlot.mainHand)).description: "") +
                getArmorDescription() ;
        return monsterDescription;
    }

}
