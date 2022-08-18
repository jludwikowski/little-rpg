package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.AdjectivesTable;
import org.littleRpg.model.MonsterTypes;
import org.littleRpg.model.Monster;

import java.util.Arrays;

public class MonsterGenerator extends Generator<Monster> {

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
                return new Monster(name, "green goblin",5,5,30,3,null, null, null);
            case slime:
                return new Monster(name, "foul smelling gelatinous mass",20,20,15,1,null, null, null);
            case orc:
                return new Monster(name, "orc",20,20,25,5,null, null, null);
        }
        return null;
    }

}
