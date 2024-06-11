package org.littleRpg.generator;

import org.littleRpg.model.Biome;
import org.littleRpg.model.Monster;
import org.littleRpg.model.SpecialType;

import java.util.ArrayList;
import java.util.List;

public class SpecialTypeGenerator extends MonsterGenerator{
    public Monster shopkeeperGenerator(Biome biome,int [] location){
        Monster baseMonster = this.getEntity(biome,location);
        baseMonster.goldCoins += 1000;
        baseMonster.maxHp += 1000;
        baseMonster.currentHp += 1000;
        baseMonster.loot.addAll(itemGenerator.getEntities(50));
        baseMonster.loot.addAll(weaponGenerator.getEntities(80));
        baseMonster.loot.addAll(armorGenerator.getEntities(80));
        baseMonster.specialType = SpecialType.shopkeeper;
        baseMonster.aggressive = false;
        return baseMonster;
    }
    public Monster guardsGenerator(Biome biome, int [] location){
        Monster baseMonster = this.getEntity(biome, location);
        baseMonster.maxHp += 500;
        baseMonster.currentHp += 500;
        baseMonster.monsterDamageReduction += 30;
        baseMonster.strength += 20;
        baseMonster.specialType = SpecialType.guard;
        baseMonster.aggressive = false;
        return baseMonster;
    }

    public Monster blacksmithGenerator(Biome biome, int [] location){
        Monster baseMonster = this.getEntity(biome,location);
        baseMonster.goldCoins += 1000;
        baseMonster.maxHp += 1000;
        baseMonster.currentHp += 1000;
        baseMonster.specialType = SpecialType.blacksmith;
        baseMonster.aggressive = false;
        return baseMonster;

    }

    public List<Monster> shopMonsterGenerator(Biome biome, int [] location){
        List<Monster> shopMonsters = new ArrayList<>();
        switch (biome) {
            case shop:
                shopMonsters.add(shopkeeperGenerator(biome,location));
                shopMonsters.add((guardsGenerator(biome,location)));
                break;
            case smithy:
                shopMonsters.add(blacksmithGenerator(biome,location));
                break;
        }
        return shopMonsters;

    }



}
