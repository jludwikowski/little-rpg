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
                Monster.MonsterBuilder goblin = new Monster.MonsterBuilder(monsterType, new Archetype(1.1f,1, 1.1f,1.1f,1.1f), 0);
                goblin.setName(name);
                goblin.setDescription("green goblin");
                goblin.setMaxHp(5);
                goblin.setCurrentHp(5);
                goblin.setMaxMana(0);
                goblin.setCurrentMana(0);
                goblin.setAttack(30);
                goblin.setStrength(3);
                goblin.setMonsterDamageReduction(0);
                goblin.setMainWeapon(weaponGenerator.getEntity());
                goblin.setMainArmor(armorGenerator.armorMapGenerator(40));
                goblin.setLoot(itemGenerator.getEntities(90));
                goblin.setGoldCoins(0);
                goblin.setSpecialType(null);
                goblin.setExp(7);
                return goblin.build();

            case slime:
                Monster.MonsterBuilder slime = new Monster.MonsterBuilder(monsterType, new Archetype(1.1f,1.1f, 1.1f,2,1.1f), 0);
                slime.setName(name);
                slime.setDescription("foul smelling gelatinous mass");
                slime.setMaxHp(20);
                slime.setCurrentHp(20);
                slime.setMaxMana(0);
                slime.setCurrentMana(0);
                slime.setAttack(15);
                slime.setStrength(1);
                slime.setMonsterDamageReduction(0);
                slime.setLoot(itemGenerator.getEntities(90));
                slime.setGoldCoins(0);
                slime.setExp(4);
                return slime.build();
            case elf:
                Monster.MonsterBuilder elf = new Monster.MonsterBuilder(monsterType, new Archetype(1.5f,2, 1.3f,1.2f,1.3f), 0);
                elf.setName(name);
                elf.setDescription("elf");
                elf.setMaxHp(20);
                elf.setCurrentHp(20);
                elf.setMaxMana(0);
                elf.setCurrentMana(0);
                elf.setAttack(18);
                elf.setStrength(5);
                elf.setMonsterDamageReduction(0);
                elf.setMainWeapon(weaponGenerator.getEntity());
                elf.setMainArmor(armorGenerator.armorMapGenerator(60));
                elf.setLoot(itemGenerator.getEntities(90));
                elf.setGoldCoins(0);
                elf.setExp(7);
                return elf.build();


            case human:
                Monster.MonsterBuilder human = new Monster.MonsterBuilder(monsterType, new Archetype(1.5f,1.2f, 1.2f,1.1f,1.2f), 0);
                human.setName(name);
                human.setDescription("human");
                human.setMaxHp(20);
                human.setCurrentHp(20);
                human.setMaxMana(0);
                human.setCurrentMana(0);
                human.setAttack(15);
                human.setStrength(6);
                human.setMonsterDamageReduction(0);
                human.setMainWeapon(weaponGenerator.getEntity());
                human.setMainArmor(armorGenerator.armorMapGenerator(65));
                human.setLoot(itemGenerator.getEntities(90));
                human.setGoldCoins(0);
                human.setExp(8);
                return human.build();


            case orc:
                Monster.MonsterBuilder orc = new Monster.MonsterBuilder(monsterType, new Archetype(1.5f,1.1f,
                        1.4f,1.2f,1.4f), 0);
                orc.setName(name);
                orc.setDescription("orc");
                orc.setMaxHp(25);
                orc.setCurrentHp(25);
                orc.setMaxMana(0);
                orc.setCurrentMana(0);
                orc.setAttack(10);
                orc.setStrength(8);
                orc.setMonsterDamageReduction(0);
                orc.setMainWeapon(weaponGenerator.getEntity());
                orc.setMainArmor(armorGenerator.armorMapGenerator(90));
                orc.setLoot(itemGenerator.getEntities(90));
                orc.setGoldCoins(0);
                orc.setExp(8);
                return orc.build();

            case ghul:
                Monster.MonsterBuilder ghul = new Monster.MonsterBuilder(monsterType, new Archetype(1.1f,1.1f, 1.1f,1.1f,1.1f), 0);
                ghul.setName(name);
                ghul.setDescription("ghul");
                ghul.setMaxHp(15);
                ghul.setCurrentHp(15);
                ghul.setMaxMana(0);
                ghul.setCurrentMana(0);
                ghul.setAttack(20);
                ghul.setStrength(5);
                ghul.setMonsterDamageReduction(0);
                ghul.setLoot(itemGenerator.getEntities(90));
                ghul.setGoldCoins(0);
                ghul.setExp(6);
                return ghul.build();


            case demon:
                Monster.MonsterBuilder demon = new Monster.MonsterBuilder(monsterType, new Archetype(1.4f,1.8f,
                        1.4f,1.2f,1.4f), 0);
                demon.setName(name);
                demon.setDescription("demon");
                demon.setMaxHp(22);
                demon.setCurrentHp(22);
                demon.setMaxMana(0);
                demon.setCurrentMana(0);
                demon.setAttack(25);
                demon.setStrength(10);
                demon.setMonsterDamageReduction(0);
                demon.setMainWeapon(weaponGenerator.getEntity());
                demon.setMainArmor(armorGenerator.armorMapGenerator(40));
                demon.setLoot(itemGenerator.getEntities(90));
                demon.setGoldCoins(0);
                demon.setExp(9);
                return demon.build();
            case ogr:
                  Monster.MonsterBuilder ogr = new Monster.MonsterBuilder(monsterType, new Archetype(1.6f,0.2f,
                        1.6f,1.6f,1.6f), 0);
                ogr.setName(name);
                ogr.setDescription("ogr");
                ogr.setMaxHp(30);
                ogr.setCurrentHp(30);
                ogr.setMaxMana(0);
                ogr.setCurrentMana(0);
                ogr.setAttack(25);
                ogr.setStrength(20);
                ogr.setMonsterDamageReduction(0);
                ogr.setMainWeapon(weaponGenerator.getEntity());
                ogr.setMainArmor(armorGenerator.armorMapGenerator(80));
                ogr.setLoot(itemGenerator.getEntities(90));
                ogr.setGoldCoins(0);
                ogr.setExp(9);
                return ogr.build();


            case giant:
                Monster.MonsterBuilder giant = new Monster.MonsterBuilder(monsterType, new Archetype(1.7f,0.2f,
                        1.9f,1.8f,1.9f), 0);
                giant.setName(name);
                giant.setDescription("giant");
                giant.setMaxHp(35);
                giant.setCurrentHp(35);
                giant.setMaxMana(0);
                giant.setCurrentMana(0);
                giant.setAttack(20);
                giant.setStrength(20);
                giant.setMonsterDamageReduction(0);
                giant.setMainWeapon(weaponGenerator.getEntity());
                giant.setMainArmor(armorGenerator.armorMapGenerator(15));
                giant.setLoot(itemGenerator.getEntities(90));
                giant.setGoldCoins(0);
                giant.setExp(8);
                return giant.build();


            case werewolf:
                Monster.MonsterBuilder werewolf = new Monster.MonsterBuilder(monsterType, new Archetype(1.4f,0.9f, 1.4f,1.2f,1.4f), 0);
                werewolf.setName(name);
                werewolf.setDescription("warewolf");
                werewolf.setMaxHp(30);
                werewolf.setCurrentHp(30);
                werewolf.setMaxMana(0);
                werewolf.setCurrentMana(0);
                werewolf.setAttack(15);
                werewolf.setStrength(10);
                werewolf.setMonsterDamageReduction(0);
                werewolf.setLoot(itemGenerator.getEntities(90));
                werewolf.setGoldCoins(0);
                werewolf.setExp(10);
                return werewolf.build();


            case vampire:
                Monster.MonsterBuilder vampire = new Monster.MonsterBuilder(monsterType, new Archetype(1.6f,1.2f,
                        1.6f,1.6f,1.6f), 0);
                vampire.setName(name);
                vampire.setDescription("vampire");
                vampire.setMaxHp(30);
                vampire.setCurrentHp(30);
                vampire.setMaxMana(0);
                vampire.setCurrentMana(0);
                vampire.setAttack(20);
                vampire.setStrength(10);
                vampire.setMonsterDamageReduction(0);
                vampire.setMainWeapon(weaponGenerator.getEntity());
                vampire.setMainArmor(armorGenerator.armorMapGenerator(75));
                vampire.setLoot(itemGenerator.getEntities(90));
                vampire.setGoldCoins(0);
                vampire.setExp(10);
                return vampire.build();
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
