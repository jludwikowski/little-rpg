package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.engine.Runner;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.littleRpg.model.Biome.*;


public class PlaceGenerator extends Generator<MapPlace> {


    MonsterGenerator monsterGenerator = new MonsterGenerator();
    ItemGenerator itemGenerator = new ItemGenerator();
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    ArmorGenerator armorGenerator = new ArmorGenerator();
    PlaceFeatureGenerator placeArchitectureGenerator = new PlaceFeatureGenerator();
    SpecialTypeGenerator specialTypeGenerator = new SpecialTypeGenerator();
    int lastId = 0;
    public Biome lastBiome;
    public Biome newBiome;
    private static String[] weatherTypes = {"sunny", "stormy", "rainy","foggy"};
    public static String [] sceneryElements = {"a river", "a small cave", "many ancient signs on the ground", "a ruined castle", "strangely twisted statues"};

    public PlaceGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"cheerful","strange","dark","foreboding","quiet"});
        this.adjectiveTypes = Arrays.asList(featureAdjective);
        this.exclusives = null;
    }

    public MapPlace getShop(int [] location){
        Biome biome = shop;
        if(Roller.pickNumberFrom(100) <10){
             biome = smithy;
        }
        MapPlace myShop = this.getBaseByType(biome, location);
        return myShop;
    }

    public MapPlace getEntity(int [] location) {
        Biome biome = Biome.values()[Roller.pickNumberFrom(Biome.values().length-1)];
        MapPlace place = this.getBaseByType(biome, location);
        place = this.finalizeEntity(place);
        return place;
    }

    @Override
    public MapPlace getEntity() {
        return getEntity(Runner.startLocation);
    }

    @Override
    public List<MapPlace> getEntities(int probability) {
        List<MapPlace> world = new ArrayList<MapPlace>();
        for(int i=0; i<=100; i++){
            world.add(this.getEntity());
        }
        return world;
    }


    public MapPlace getEntity(MapPlace lastPlace, int [] location) {
        Biome biome = biomeGenerator(lastPlace);
        MapPlace place = this.getBaseByType(biome, location);
        place = this.finalizeEntity(place);
        return place;
    }

    public Biome biomeGenerator (Place place) {

       if (place == null){
            this.newBiome = Biome.values()[Roller.pickNumberFrom(Biome.values().length)];
        }
       else {
           this.lastBiome = place.biome;
           int index = Arrays.binarySearch(Biome.values(), lastBiome);
           double roll = Math.random()*100;

           if (roll <50) {
               this.newBiome = lastBiome;
           }

           if (roll > 50 && roll < 75 && index != Biome.values().length - 1){
               this.newBiome = Biome.values()[index + 1];
           }
           if(roll >= 75 && index != 0) {
               this.newBiome = Biome.values()[index - 1];
           }
           if(roll > 50 && roll < 75 && index == Biome.values().length - 1) {
               this.newBiome = Biome.values()[0];
           }
           if(roll >= 75 && index == 0) {
               this.newBiome = Biome.values()[Biome.values().length-1];
           }

        }
        return this.newBiome;
    }



    @Override
    public MapPlace adjust(MapPlace entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;
        return entity;
    }

    private Random random = new Random();

    public String generateDescription(Biome biome) {
        String weather = weatherTypes[random.nextInt(weatherTypes.length)];
        String sceneryElement = sceneryElements[random.nextInt(sceneryElements.length)];

        return String.format("You find yourself in a %s. The weather is %s. Nearby, there is %s.",biome, weather, sceneryElement);
    }



    private MapPlace getBaseByType(Biome biome, int [] location) {
        String name = biome.toString();
        switch(biome) {
            case desert:
                return new MapPlace(lastId++, biome, name,generateDescription(desert),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case mountain:
                return new MapPlace(lastId++, biome, name,generateDescription(mountain),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case hill:
                return new MapPlace(lastId++, biome, name,generateDescription(hill),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case forest:
                return new MapPlace(lastId++, biome, name,generateDescription(forest),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case meadow:
                return new MapPlace(lastId++, biome, name,generateDescription(meadow),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case swamp:
                return new MapPlace(lastId++, biome, name,generateDescription(swamp),null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case shop:
                return new MapPlace(lastId++, biome, name,generateDescription(shop),null,specialTypeGenerator.shopMonsterGenerator(biome, location), itemGenerator.getEntities(80),null );
            case smithy:
                return new MapPlace(lastId++, biome, name,generateDescription(smithy),null,specialTypeGenerator.shopMonsterGenerator(biome, location), null,null );
        }
        return null;
    }

}
