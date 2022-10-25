package org.littleRpg.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.littleRpg.model.*;

public class Monster extends GameEntity {

    public int maxHp = 1;
    public int currentHp = 1;
    public int attack = 0;
    public int strength = 0;
    public Weapon mainWeapon = null;
    public Armor armor = null;
    public List<Item> loot;


    public Monster(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot) {
        super(name, description);
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attack = attack;
        this.strength = strength;
        this.mainWeapon = mainWeapon;
        this.armor = armor;
        this.loot = loot;

    }

    public List<Item> dropItems() {
        List<Item> dropedItems = new ArrayList<Item>();
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
        return "maxHp: " + String.valueOf(this.maxHp) + "\n" + "currentHP: " + String.valueOf(this.currentHp) + "\n" +
                "attack: " + String.valueOf(this.attack) + "\n" + "strength: " + String.valueOf(this.strength);

    }

    public int getAttack() {
        if(this.mainWeapon != null) {
            return this.mainWeapon.bonusAttack + this.attack;
        }
        return this.attack;
    }
    public int getDamage() {
        if(this.mainWeapon != null) {
            return this.mainWeapon.baseDamageValue + this.strength;
        }
        return this.strength;
    }

    public void damage(int damageValue) {
        int damageReduction = 0;
        if (this.armor != null) {
            damageReduction = this.armor.damageReduction;
        }
        int actuallDamageValue = damageValue - damageReduction < 0 ? 0 : damageValue - damageReduction;
        // int actuallDamageValue = damageValue - this.mainArmor.damageReduction;
        this.currentHp = this.currentHp - actuallDamageValue;
        if(this.currentHp <=0 ){
            System.out.println("DEAD");
        }
    }

    public void heal(int healValue) {
        this.currentHp = this.currentHp + healValue > this.maxHp ? this.maxHp : this.currentHp + healValue;
    }

    public void adjust(Monster adjust){
        this.name = adjust.name != null ? adjust.name + " " + this.name : this.name;
        this.description = adjust.description + " " + this.description;
        this.currentHp += adjust.currentHp;
        this.maxHp += adjust.maxHp;
        this.attack += adjust.attack;
        this.strength += adjust.strength;
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
