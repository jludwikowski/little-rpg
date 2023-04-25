package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.AdjectivesTable;
import org.littleRpg.model.MonsterTypes;
import org.littleRpg.model.Monster;

import java.util.Arrays;

public class MonsterGenerator extends Generator<Monster> {

    ArmorGenerator armorGenerator = new ArmorGenerator();
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    ItemGenerator itemGenerator = new ItemGenerator();

    public MonsterGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","dark","pale"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;
    }

    @Override
    public Monster getEntity() {
        MonsterTypes type = MonsterTypes.values()[Roller.pickNumberFrom(MonsterTypes.values().length)];
        Monster baseMonster =  this.getBaseByType(type);
        baseMonster = this.finalizeEntity(baseMonster);
        return baseMonster;
    }

    @Override
    public Monster adjust(Monster entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;

        switch(adj) {
            case "small":
                entity.maxHp = new Double(Math.floor(entity.maxHp * 0.8)).intValue();
                entity.currentHp = entity.maxHp;
                entity.strength -= 1;
                break;
            case "big":
                entity.maxHp = new Double(Math.floor(entity.maxHp * 1.2)).intValue();
                entity.currentHp = entity.maxHp;
                entity.strength += 1;
                break;
            case "huge":
                entity.maxHp = new Double(Math.floor(entity.maxHp * 1.6)).intValue();
                entity.currentHp = entity.maxHp;
                entity.attack -= 5;
                entity.strength += 2;
                break;
            case "enormous":
                entity.maxHp = new Double(Math.floor(entity.maxHp * 2)).intValue();
                entity.currentHp = entity.maxHp;
                entity.attack -= 5;
                entity.strength += 4;
                break;
            case "strange":
                entity.attack += 5;
                break;
            case "dark":
                entity.maxHp = new Double(Math.floor(entity.maxHp * 0.8)).intValue();
                entity.currentHp = entity.maxHp;
                entity.attack += 5;
                break;
        }
        return entity;
    }

    public Monster getBaseByType(MonsterTypes monsterType) {
        String name = monsterType + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(monsterType) {
            case goblin:
                return new Monster(monsterType, name, "green goblin",5,5, 0,0,30,3, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case slime:
                return new Monster(monsterType, name, "foul smelling gelatinous mass",20,20,0, 0,15,1,0, null, null, itemGenerator.getEntities(30), null);
            case elf:
                return new Monster(monsterType, name, "elf",20,20,0,0, 18,5, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case human:
                return new Monster(monsterType, name, "human",20,20,0,0, 15,6, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case orc:
                return new Monster(monsterType, name, "orc",25,25,0,0,10,8,0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case ghul:
                return new Monster(monsterType, name, "ghul",15,15,0,0,20,5,0, null, null, itemGenerator.getEntities(90), null);
            case demon:
                return new Monster(monsterType, name, "demon",22,22,0,0,25,10,0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case ogr:
                return new Monster(monsterType, name, "ogr",30,30,0,0,25,20, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case giant:
                return new Monster(monsterType, name, "giant",35,35,0,0,20,20, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);
            case werewolf:
                return new Monster(monsterType, name, "werewolf",30,30,0,0,15,10,0, null, null, itemGenerator.getEntities(90), null);
            case vampire:
                return new Monster(monsterType, name, "vampire",30,30,0,0,20,10, 0, weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90), null);

        }
        return null;
    }



}
