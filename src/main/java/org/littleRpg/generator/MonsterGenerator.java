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
    public Monster Adjust(Monster entity, String adj) {
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
        Monster monster = new Monster();

        switch(mosterType) {
            case goblin:
                monster.name = "goblin " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 5;
                monster.currentHp = 5;
                monster.strength = 3;
                monster.attack = 30;
                monster.description = "green goblin";
                break;
            case slime:
                monster.name = "slime " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 20;
                monster.currentHp = 20;
                monster.strength = 1;
                monster.attack = 15;
                monster.description = "foul smelling gelatinous mass";
                break;
            case orc:
                monster.name = "orc " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 20;
                monster.currentHp = 20;
                monster.strength = 5;
                monster.attack = 25;
                monster.description = "orc";
                break;
            default:
                return null;
        }
        return monster;
    }

}
