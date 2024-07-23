package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.engine.Runner;
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
    MonsterTypes[] caveMonsters = {MonsterTypes.vampire, MonsterTypes.ghul, MonsterTypes.goblin};
    Map<Biome,MonsterTypes[]> biomeMonsterTypesMap = new HashMap<>(Map.of(Biome.desert,desertMonsters,
            Biome.mountain, mountainMonsters,
            Biome.hill, hillMonsters,
            Biome.forest, forestMonsters,
            Biome.meadow, meadowMonsters,
            Biome.swamp, swampMonsters,
            Biome.shop, shopMonsters,
            Biome.smithy, shopMonsters,
            Biome.cave, caveMonsters));

    Random random =new Random();

    public MonsterGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","dark","pale"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;
    }

    public Monster getEntity(Biome biome, int [] location){
        MonsterTypes[] biomeMonsters = biomeMonsterTypesMap.get(biome);
        return getMonster(biomeMonsters, location);
    }




    private double calculateDistance(int[] location1, int[] location2) {
        int x1 = location1[0];
        int y1 = location1[1];
        int z1 = location1[2];
        int x2 = location2[0];
        int y2 = location2[1];
        int z2 = location2[2];

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }


    public Monster getMonster(MonsterTypes[] monsterTypesArray, int [] location){
        MonsterTypes type = monsterTypesArray[Roller.pickNumberFrom(monsterTypesArray.length)];
        Monster baseMonster = this.getBaseByType(type);
        baseMonster = this.finalizeEntity(baseMonster);
        double distance = calculateDistance(location, Runner.startLocation);
        int levelMultiplier = (int) (distance / 10)+1;
        baseMonster.monsterLevel = random.nextInt(levelMultiplier);
        baseMonster.checkLevel();
        return baseMonster;
    }

    public List<Monster> getEntities(int probability, Biome biome, int[] location) {
        List<Monster> entities = new ArrayList<Monster>();
        while(Math.random()*100 < probability){
            entities.add(this.getEntity(biome, location));
        }
        return entities;
    }


    @Override
    public Monster getEntity() {
        return getEntity(Biome.shop, Runner.startLocation);
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
                return new Monster(monsterType, name, "green goblin",5,5, 0,
                        0,30,3, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(40), itemGenerator.getEntities(90),
                        null, 0, null, 7,0, new Archetype(1.1f,1,
                        1.1f,1.1f,1.1f));
            case slime:
                return new Monster(monsterType, name, "foul smelling gelatinous mass",20,20,0,
                        0,15,1,0, null, null,
                        itemGenerator.getEntities(30), null, 0, null, 4,0,
                        new Archetype(1.1f,1.1f, 1.1f,2,1.1f));
            case elf:
                return new Monster(monsterType, name, "elf",20,20,0,
                        0, 18,5, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(60), itemGenerator.getEntities(90),
                        null,0, null,0,0, new Archetype(1.5f,2,
                        1.3f,1.2f,1.3f));
            case human:
                return new Monster(monsterType, name, "human",20,20,0,0,
                        15,6, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(65), itemGenerator.getEntities(90),
                        null,0, null,0,0, new Archetype(1.5f,1.2f,
                        1.2f,1.1f,1.2f));
            case orc:
                return new Monster(monsterType, name, "orc",25,25,0,0,
                        10,8,0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(90), itemGenerator.getEntities(90),
                        null,0, null,0,0,new Archetype(1.5f,1.1f,
                        1.4f,1.2f,1.4f));
            case ghul:
                return new Monster(monsterType, name, "ghul",15,15,0,0,
                        20,5,0, null, null,
                        itemGenerator.getEntities(90), null,0, null,0,0,
                        new Archetype(1.1f,1.1f, 1.1f,1.1f,1.1f));
            case demon:
                return new Monster(monsterType, name, "demon",22,22,0,0,
                        25,10,0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(40), itemGenerator.getEntities(90),
                        null,0, null,0,0,new Archetype(1.4f,1.8f,
                        1.4f,1.2f,1.4f));
            case ogr:
                return new Monster(monsterType, name, "ogr",30,30,0,0,
                        25,20, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(80), itemGenerator.getEntities(90),
                        null,0, null,0,0,new Archetype(1.6f,0.2f,
                        1.6f,1.6f,1.6f));
            case giant:
                return new Monster(monsterType, name, "giant",35,35,0,0,
                        20,20, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(15), itemGenerator.getEntities(90),
                        null,0, null,0,0,new Archetype(1.7f,0.2f,
                        1.9f,1.8f,1.9f));
            case werewolf:
                return new Monster(monsterType, name, "werewolf",30,30,0,
                        0,15,10,0, null, null,
                        itemGenerator.getEntities(90), null,0, null,0,0,
                        new Archetype(1.4f,0.9f, 1.4f,1.2f,1.4f));
            case vampire:
                return new Monster(monsterType, name, "vampire",30,30,0,
                        0,20,10, 0, weaponGenerator.getEntity(),
                        armorGenerator.armorMapGenerator(75), itemGenerator.getEntities(90),
                        null,0, null,0,0,new Archetype(1.6f,1.2f,
                        1.6f,1.6f,1.6f));
            /*case seller:
                return new Monster(monsterType, name, "seller", 999,999,0,0,999,999,999,null,armorGenerator.armorMapGenerator(75),itemGenerator.getEntities(99),null, 1000);*/

        }
        return null;
    }

    private Monster getAdjust(String adj) {
        switch (adj) {
            case "big":
                return new Monster(null, adj, adj, 1.2F, 1.2F, 0, 0, 0,
                        1, 0, null, null, null, null,
                        0, null,1.2F,0,null);
            case "small":
                return new Monster(null, adj, adj, 0.8F, 0.8F, 0, 0, 3,
                        -1, 0, null, null, null, null,
                        0, null,0,0,null);
            case "huge":
                return new Monster(null, adj, adj, 1.5F, 1.5F, 0, 0, -5,
                        2, 0, null, null, null, null,
                        0, null,0,0,null);
            case "enormous":
                return new Monster(null, adj, adj, 2F, 2F, 0, 0, 5,
                        2, 0, null, null, null, null,
                        0, null,0,0,null);
            case "strange":
                return new Monster(null, adj, adj, 1F, 1F, 0, 0, 5,
                        0, 0, null, null, null, null,
                        0, null,0,0,null);
            case "dark":
                return new Monster(null, adj, adj, 0.8F, 0.8F, 0, 0, 5,
                        0, 0, null, null, null, null,
                        0, null,0,0,null);
            case "ugly":
                return new Monster(null, adj, adj, 1.1F, 1.1F, 0, 0, 1,
                        1, 0, null, null, null, null,
                        0, null,0,0,null);
            case "pale":
                return new Monster(null, adj, adj, 0.9F, 0.9F, 0, 0, 2,
                        2, 0, null, null, null, null,
                        0, null,0,0,null);

        }
        return null;
    }
}
