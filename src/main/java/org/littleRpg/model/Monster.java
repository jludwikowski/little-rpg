package org.littleRpg.model;

import java.io.Serializable;
import java.util.*;

public class Monster extends GameEntity implements Serializable {

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
    public Item mainNecklace = null;
    public Item mainRing = null;



    public Monster(MonsterTypes type, String name, String description, int maxHp, int currentHp, int maxMana, int currentMana, int attack, int strength, int monsterDamageReduction, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill> skills, Item mainNecklace, Item mainRing) {
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
        this.mainNecklace = mainNecklace;
        this.mainRing = mainRing;


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
                "damageReduction: " + String.valueOf(finalDamageReduction);

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
                if (this.armor != null) {
                    if (mainRing != null && mainRing.effect == "elfBlessing"){
                        monsterDamageReduction = monsterDamageReduction + mainRing.power;
                    }
                    if (mainNecklace != null && mainNecklace.effect == "elfBlessing"){
                        monsterDamageReduction = monsterDamageReduction + mainNecklace.power;
                    }
                    return armor.damageReduction + monsterDamageReduction;
                }
                return this.monsterDamageReduction;

            case Strength:
                if (mainRing != null && mainRing.effect == "ancientPower"){
                    strength = strength + mainRing.power;
                }
                if (mainNecklace != null && mainNecklace.effect == "ancientPower"){
                    strength = strength + mainNecklace.power;
                }
                return strength;
            case maxHp:
                if (mainRing != null && mainRing.effect == "stoneGolem"){
                    maxHp = maxHp + mainRing.power;
                }
                if (mainNecklace != null && mainNecklace.effect == "stoneGolem"){
                    maxHp = maxHp + mainNecklace.power;
                }
                return maxHp;
            case attack:
                if(mainWeapon != null) {
                    if (mainRing != null && mainRing.effect == "demonicRage"){
                        attack = attack + mainRing.power;
                    }
                    if (mainNecklace != null && mainNecklace.effect == "demonicRage"){
                        attack = attack + mainNecklace.power;
                    }
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
        this.mainNecklace = adjust.mainNecklace != null ? adjust.mainNecklace: this.mainNecklace;
        this.mainRing = adjust.mainRing != null ? adjust.mainRing: this.mainRing;

    }

    public static void showEquipItems(Weapon mainWeapon, Armor armor, Item mainNecklace, Item mainRing){
        if(mainWeapon != null){
            System.out.println("Equiped Weapon - " + mainWeapon.description);
        }
        if(armor != null){
            System.out.println("Equiped armor - " + armor.description);
        }
        if(mainNecklace != null){
            System.out.println("Equiped necklace - " + mainNecklace.description);
        }
        if(mainRing != null){
            System.out.println("Equiped ring - " + mainRing.description);
        }
        if(mainWeapon == null && armor == null){
            System.out.println("No equip Weapon or armor");
        }
        if(mainNecklace == null && mainRing == null) {
            System.out.println("No equip Item");
        }

    }

    public String getDescription() {
        String monsterDescription = description +
                ((mainWeapon != null) ? "\n armed with " + mainWeapon.description: "") +
                ((armor != null) ? "\n wearing " + armor.description : "") +
                ((mainNecklace != null) ? "\n wearing" + mainNecklace.description : "") +
                ((mainRing != null) ? "\n wearing" + mainRing.description : "");
        return monsterDescription;
    }

}
