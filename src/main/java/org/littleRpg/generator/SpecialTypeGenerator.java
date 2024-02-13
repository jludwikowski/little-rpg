package org.littleRpg.generator;

import org.littleRpg.model.Biome;
import org.littleRpg.model.Monster;

import java.util.List;

public class SpecialTypeGenerator extends MonsterGenerator{
    public Monster shopkeeperGenerator(Biome biome){
        Monster baseMonster = this.getEntity(biome);
        baseMonster.goldCoins += 1000;
        baseMonster.maxHp += 1000;
        baseMonster.currentHp += 1000;
        baseMonster.loot.addAll(itemGenerator.getEntities(50));
        baseMonster.loot.addAll(weaponGenerator.getEntities(80));
        baseMonster.loot.addAll(armorGenerator.getEntities(80));
        //shopkeeperList.add(baseMonster);
        return baseMonster;
    }



}
