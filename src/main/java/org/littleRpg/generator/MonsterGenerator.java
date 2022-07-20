package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.MonsterTypes;
import org.littleRpg.model.Monster;
import java.util.List;

public class MonsterGenerator extends Generator<Monster> {

    @Override
    public Monster getEntity() {
        MonsterTypes type = MonsterTypes.values()[Roller.pickNumberFrom(MonsterTypes.values().length)];
        Monster baseMonster =  this.getBaseByType(type);
        return baseMonster;
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
                monster.description = "Small green ugly goblin";
                break;
            case slime:
                monster.name = "slime " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 20;
                monster.currentHp = 20;
                monster.strength = 1;
                monster.attack = 15;
                monster.description = "Small foul smelling gelatinous mass";
                break;
            default:
                return null;
        }
        return monster;
    }

    public Monster getSizeAdjustment(List<String> sizes, Monster baseMonster){

        return baseMonster;
    }

}
