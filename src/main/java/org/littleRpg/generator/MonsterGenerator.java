package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.*;

public class MonsterGenerator extends Generator<Monster> {

    //public List<Armor> mainArmor = new ArrayList<>();
    ArmorGenerator armorGenerator = new ArmorGenerator();
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    ItemGenerator itemGenerator = new ItemGenerator();
    MonsterTypes[] desertMonsters = {MonsterTypes.human, MonsterTypes.ghul, MonsterTypes.slime};
    MonsterTypes[] mountainMonsters = {MonsterTypes.giant, MonsterTypes.orc, MonsterTypes.demon};
    MonsterTypes[] hillMonsters = {MonsterTypes.ogr, MonsterTypes.vampire, MonsterTypes.human};
    MonsterTypes[] forestMonsters = {MonsterTypes.ogr, MonsterTypes.elf, MonsterTypes.werewolf};
    MonsterTypes[] meadowMonsters = {MonsterTypes.ogr, MonsterTypes.vampire, MonsterTypes.slime};
    MonsterTypes[] swampMonsters =  {MonsterTypes.human, MonsterTypes.ghul, MonsterTypes.goblin};
    MonsterTypes[] shopMonsters = {MonsterTypes.human, MonsterTypes.elf, MonsterTypes.goblin};
    Map<Biome,MonsterTypes[]> biomeMonsterTypesMap = new HashMap<>(Map.of(Biome.desert,desertMonsters,
            Biome.mountain, mountainMonsters,
            Biome.hill, hillMonsters,
            Biome.forest, forestMonsters,
            Biome.meadow, meadowMonsters,
            Biome.swamp, swampMonsters,
            Biome.shop, shopMonsters));



    public MonsterGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","dark","pale"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;
    }

    public Monster getEntity(Biome biome){
        MonsterTypes[] biomeMonsters = biomeMonsterTypesMap.get(biome);
        return getMonster(biomeMonsters);
    }

    @Override
    public Monster getEntity() {
        return getMonster(MonsterTypes.values());
    }

    private Monster getMonster(MonsterTypes[] monsterTypesArray){
        MonsterTypes type = monsterTypesArray[Roller.pickNumberFrom(monsterTypesArray.length)];
        Monster baseMonster = this.getBaseByType(type);
        baseMonster = this.finalizeEntity(baseMonster);
        return baseMonster;
    }

    public List<Monster> getEntities(int probability, Biome biome) {
        List<Monster> entities = new ArrayList<Monster>();
        while(Math.random()*100 < probability){
            entities.add(this.getEntity(biome));
        }
        return entities;
    }

    public List<Monster> getEntities(Biome biome) {
        List<Monster> entities = new ArrayList<Monster>();
        entities.add(this.getEntity(biome));
        return entities;
    }

    @Override
    public Monster adjust(Monster entity, String adj) {
        entity.adjust(this.getAdjust(adj));
        return entity;
    }


    public Monster getBaseByType(MonsterTypes monsterType) {
        String name = monsterType + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(monsterType) {
            case goblin:
                return new Monster(monsterType, name, "green goblin",5,5, 0,0,30,3, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(40), itemGenerator.getEntities(90), null, 0);
            case slime:
                return new Monster(monsterType, name, "foul smelling gelatinous mass",20,20,0, 0,15,1,0, null, null, itemGenerator.getEntities(30), null, 0);
            case elf:
                return new Monster(monsterType, name, "elf",20,20,0,0, 18,5, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(60), itemGenerator.getEntities(90), null,0);
            case human:
                return new Monster(monsterType, name, "human",20,20,0,0, 15,6, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(65), itemGenerator.getEntities(90), null,0);
            case orc:
                return new Monster(monsterType, name, "orc",25,25,0,0,10,8,0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(90), itemGenerator.getEntities(90), null,0);
            case ghul:
                return new Monster(monsterType, name, "ghul",15,15,0,0,20,5,0, null, null, itemGenerator.getEntities(90), null,0);
            case demon:
                return new Monster(monsterType, name, "demon",22,22,0,0,25,10,0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(40), itemGenerator.getEntities(90), null,0);
            case ogr:
                return new Monster(monsterType, name, "ogr",30,30,0,0,25,20, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(80), itemGenerator.getEntities(90), null,0);
            case giant:
                return new Monster(monsterType, name, "giant",35,35,0,0,20,20, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(15), itemGenerator.getEntities(90), null,0);
            case werewolf:
                return new Monster(monsterType, name, "werewolf",30,30,0,0,15,10,0, null, null, itemGenerator.getEntities(90), null,0);
            case vampire:
                return new Monster(monsterType, name, "vampire",30,30,0,0,20,10, 0, weaponGenerator.getEntity(), armorGenerator.armorMapGenerator(75), itemGenerator.getEntities(90), null,0);
            /*case seller:
                return new Monster(monsterType, name, "seller", 999,999,0,0,999,999,999,null,armorGenerator.armorMapGenerator(75),itemGenerator.getEntities(99),null, 1000);*/

        }
        return null;
    }

    private Monster getAdjust(String adj) {
        switch (adj) {
            case "big":
                return new Monster(null, adj, adj, 1.2F, 1.2F, 0, 0, 0, 1, 0, null, null, null, null,0);
            case "small":
                return new Monster(null, adj, adj, 0.8F, 0.8F, 0, 0, 3, -1, 0, null, null, null, null,0);
            case "huge":
                return new Monster(null, adj, adj, 1.5F, 1.5F, 0, 0, -5, 2, 0, null, null, null, null,0);
            case "enormous":
                return new Monster(null, adj, adj, 2F, 2F, 0, 0, 5, 2, 0, null, null, null, null,0);
            case "strange":
                return new Monster(null, adj, adj, 1F, 1F, 0, 0, 5, 0, 0, null, null, null, null,0);
            case "dark":
                return new Monster(null, adj, adj, 0.8F, 0.8F, 0, 0, 5, 0, 0, null, null, null, null,0);
            case "ugly":
                return new Monster(null, adj, adj, 1.1F, 1.1F, 0, 0, 1, 1, 0, null, null, null, null,0);
            case "pale":
                return new Monster(null, adj, adj, 0.9F, 0.9F, 0, 0, 2, 2, 0, null, null, null, null,0);

        }
        return null;
    }
}
