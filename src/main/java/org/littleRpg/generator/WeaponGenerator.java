package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.Weapon;
import org.littleRpg.model.WeaponTypes;
import org.littleRpg.model.AdjectivesTable;

import java.util.Arrays;

public class WeaponGenerator extends Generator<Weapon>{

    public WeaponGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(70, new String[] {"ornate","sturdy","bloody","poisoned","exceptional","elf-crafted"});
        AdjectivesTable timeAdjective = new AdjectivesTable(60, new String[] {"old","weathered","new","ancient"});

        this.adjectiveTypes = Arrays.asList(timeAdjective, featureAdjective);
        this.exclusives = null;
    }
    @Override
    public Weapon getEntity() {
        WeaponTypes type = WeaponTypes.values()[Roller.pickNumberFrom(WeaponTypes.values().length)];
        Weapon weapon = this.getBaseByType(type);
        weapon = this.finalizeEntity(weapon);
        return weapon;

    }



    public Weapon adjust(Weapon entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;

        switch(adj) {
            case "ornate":
                entity.weight = new Double(Math.floor(entity.weight * 1.2)).intValue();
                break;
            case "sturdy":
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 1.2)).intValue();
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 1.3)).intValue();
                break;
            case "bloody":
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 1.3)).intValue();
                break;
            case "poisoned":
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 1.3)).intValue();
                break;
            case "exceptional":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 1.1)).intValue();
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 1.4)).intValue();
                break;
            case "elf-crafted":
                entity.weight = new Double(Math.floor(entity.weight * 0.7)).intValue();
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 1.3)).intValue();
                break;
            case "old":
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 0.9)).intValue();
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 0.9)).intValue();
                break;
            case "weathered":
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 0.9)).intValue();
                break;
            case "new":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 1.1)).intValue();
                break;
            case "ancient":
                entity.weight = new Double(Math.floor(entity.weight * 0.7)).intValue();
                entity.bonusAttack = new Double(Math.floor(entity.bonusAttack * 0.8)).intValue();
                entity.baseDamageValue = new Double(Math.floor(entity.baseDamageValue * 0.7)).intValue();
                break;
        }
        return entity;
    }
    private Weapon getBaseByType(WeaponTypes type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(type) {
            case stick:
                return new Weapon(name, type.toString(), 3, 2,2);
            case axe:
                return new Weapon(name, type.toString(), 10, 10, 7);
            case sword:
                return new Weapon(name, type.toString(), 8, 9, 6);
            case bow:
                return new Weapon(name, type.toString(), 5, 6,5);


        }
        return null;
    }
}
