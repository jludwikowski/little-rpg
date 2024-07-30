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
    public SpecialType specialType;
    public boolean aggressive = true;
    public float exp;
    public int monsterLevel;
    public Archetype archetype;



    public Monster(MonsterTypes type, String name, String description, float maxHp, float currentHp, int maxMana,
                   int currentMana, int attack, int strength, int monsterDamageReduction, Weapon mainWeapon,
                   Map<WearSlot, Armor> mainArmor, List<Item> loot, List<Skill> skills, int goldCoins,
                   SpecialType specialType, float exp, int monsterLevel, Archetype archetype) {
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
        this.specialType = specialType;
        this.exp = exp;
        this.monsterLevel = monsterLevel;
        if(archetype == null){
            this.archetype = new Archetype(1,1,1,1,1);
        }else {
            this.archetype = archetype;
        }
    }

    public Monster(MonsterBuilder builder){
        super(builder.name, builder.description, builder.loot, builder.mainWeapon, builder.mainArmor);
        this.type = builder.type;
        this.maxHp = builder.maxHp;
        this.currentHp = builder.currentHp;
        this.maxMana = builder.maxMana;
        this.currentMana = builder.currentMana;
        this.attack = builder.attack;
        this.strength = builder.strength;
        this.monsterDamageReduction = builder.monsterDamageReduction;
        this.goldCoins = builder.goldCoins;
        this.specialType = builder.specialType;
        this.exp = builder.exp;
        this.monsterLevel = builder.monsterLevel;
        if(archetype == null){
            this.archetype = new Archetype(1,1,1,1,1);
        }else {
            this.archetype = builder.archetype;
        }

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



    public void checkLevel(){
         this.maxHp += (archetype.maxHp * monsterLevel);
         this.maxMana += (archetype.maxMana * monsterLevel);
         this.attack += (archetype.attack * monsterLevel);
         this.monsterDamageReduction += (archetype.armor * monsterLevel);
         this.strength += (archetype.strength * monsterLevel);
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
        this.exp += adjust.exp * this.exp;
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
        return description;
    }

    public static class MonsterBuilder{
        private float maxHp = 1;
        private float currentHp = 1;
        private int maxMana = 1;
        private int currentMana = 1;
        private int attack = 0;
        private int strength = 0;
        private int monsterDamageReduction = 0;

        private MonsterTypes type;
        private List<Effect> activeEffects = new ArrayList<>();
        private int goldCoins;
        private SpecialType specialType;
        private boolean aggressive = true;
        private float exp;
        private int monsterLevel;
        private Archetype archetype;
        private String name;
        private String description;
        private List<Item> loot;
        private Weapon mainWeapon;
        private Map<WearSlot, Armor> mainArmor;



        public MonsterBuilder(MonsterTypes type, Archetype archetype, int monsterLevel){
            this.type = type;
            this.archetype = archetype;
            this.monsterLevel = monsterLevel;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setLoot(List<Item> loot) {
            this.loot = loot;
        }

        public void setMainWeapon(Weapon mainWeapon) {
            this.mainWeapon = mainWeapon;
        }

        public void setMainArmor(Map<WearSlot, Armor> mainArmor) {
            this.mainArmor = mainArmor;
        }

        public void setMaxHp(float maxHp) {
            this.maxHp = maxHp;
        }

        public void setCurrentHp(float currentHp) {
            this.currentHp = currentHp;
        }

        public void setMaxMana(int maxMana) {
            this.maxMana = maxMana;
        }

        public void setCurrentMana(int currentMana) {
            this.currentMana = currentMana;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }

        public void setMonsterDamageReduction(int monsterDamageReduction) {
            this.monsterDamageReduction = monsterDamageReduction;
        }

        public void setActiveEffects(List<Effect> activeEffects) {
            this.activeEffects = activeEffects;
        }

        public void setGoldCoins(int goldCoins) {
            this.goldCoins = goldCoins;
        }

        public void setSpecialType(SpecialType specialType) {
            this.specialType = specialType;
        }

        public void setAggressive(boolean aggressive) {
            this.aggressive = aggressive;
        }

        public void setExp(float exp) {
            this.exp = exp;
        }

        public Monster build(){
            return new Monster(this);
        }
    }

}
