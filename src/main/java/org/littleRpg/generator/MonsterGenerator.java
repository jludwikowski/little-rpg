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

    private Monster getBaseByType(MonsterTypes mosterType) {
        String name = mosterType + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(mosterType) {
            case goblin:
                return new Monster(name, "green goblin",5,5,30,3,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));
            case slime:
                return new Monster(name, "foul smelling gelatinous mass",20,20,15,1,null, null, itemGenerator.getEntities(30));
            case orc:
                return new Monster(name, "orc",20,20,25,5,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));
            case ghul:
                return new Monster(name, "ghul",15,15,20,5,null, null, itemGenerator.getEntities(90));
            case demon:
                return new Monster(name, "demon",22,22,25,10,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));
            case ogr:
                return new Monster(name, "ogr",30,30,25,20,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));
            case giant:
                return new Monster(name, "giant",35,35,20,20,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));
            case werewolf:
                return new Monster(name, "werewolf",30,30,15,10,null, null, itemGenerator.getEntities(90));
            case vampire:
                return new Monster(name, "vampire",30,30,20,10,weaponGenerator.getEntity(), armorGenerator.getEntity(), itemGenerator.getEntities(90));

        }
        return null;
    }



}
