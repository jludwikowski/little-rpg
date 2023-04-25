package org.littleRpg.model;

import java.util.*;

public class Monster extends GameEntity {

    public int maxHp = 1;
    public int currentHp = 1;
    public int maxMana = 1;
    public int currentMana = 1;
    public int attack = 0;
    public int strength = 0;
    public int monsterDamageReduction = 0;
    public Weapon mainWeapon = null;
    public Armor armor = null;
    public List<Item> loot = new ArrayList<>();
    public MonsterTypes type;
    public int healValue = 0;


    public Monster(MonsterTypes type, String name, String description, int maxHp, int currentHp, int maxMana, int currentMana, int attack, int strength, int monsterDamageReduction, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill> skills) {
        super(name, description);
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.maxMana = maxMana;
        this.currentMana = currentMana;
        this.attack = attack;
        this.strength = strength;
        this.monsterDamageReduction = monsterDamageReduction;
        this.mainWeapon = mainWeapon;
        this.armor = armor;
        this.loot = loot;

    }

    public List<Item> dropItems() {
        List<Item> dropedItems = new ArrayList<>();
        if (this.loot != null) {
            dropedItems.addAll(this.loot);
            this.loot.clear();
        }
        if (this.mainWeapon != null) {
            dropedItems.add(this.mainWeapon);
            this.mainWeapon = null;
        }
        if (this.armor != null) {
            dropedItems.add(this.armor);
            this.armor = null;
        }
        return dropedItems;
    }

    public String getStats() {
        int finalDamageReduction = this.armor != null ? this.monsterDamageReduction + armor.damageReduction : this.monsterDamageReduction;
        return "maxHp: " + String.valueOf(this.maxHp) + "\n" + "currentHP: " + String.valueOf(this.currentHp) + "\n" +
                "attack: " + String.valueOf(this.attack) + "\n" + "strength: " + String.valueOf(this.strength) + "\n" +
                "damageReduction" + String.valueOf(finalDamageReduction);

    }

    public int getDamage() {
        if(this.mainWeapon != null) {
            return this.mainWeapon.baseDamageValue + this.strength;
        }
        return this.strength;
    }

    public int getBaseAttribute(Attribute attribute){
        switch (attribute) {
            case monsterDamageReduction:
                if (this.armor == null) {
                    return this.monsterDamageReduction;
                }
                return armor.damageReduction + monsterDamageReduction;
            case Strength:
                return strength;
            case maxHp:
                return maxHp;
            case attack:
                if(mainWeapon != null) {
                    return mainWeapon.bonusAttack + attack;
                }
                return attack;
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
        this.currentHp += adjust.currentHp;
        this.maxHp += adjust.maxHp;
        this.attack += adjust.attack;
        this.strength += adjust.strength;
        this.monsterDamageReduction += adjust.monsterDamageReduction;
        this.mainWeapon = adjust.mainWeapon != null ? adjust.mainWeapon: this.mainWeapon;
        this.armor = adjust.armor != null ? adjust.armor: this.armor;
    }

    public static void showEquipItems(Weapon mainWeapon, Armor armor){
        if(mainWeapon != null){
            System.out.println("Equiped Weapon - " + mainWeapon.description);
        }
        if(armor != null){
            System.out.println("Equiped armor - " + armor.description);
        }
        if(mainWeapon == null && armor == null){
            System.out.println("No equip Weapon or armor");
        }

    }

    public String getDescription() {
        String monsterDescription = description +
                ((mainWeapon != null) ? "\n armed with " + mainWeapon.description: "") +
                ((armor != null) ? "\n wearing " + armor.description : "");
        return monsterDescription;
    }

}
