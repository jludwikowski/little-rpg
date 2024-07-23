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
    DescriptionGenerator descriptionGenerator = new DescriptionGenerator();
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    ArmorGenerator armorGenerator = new ArmorGenerator();
    PlaceFeatureGenerator placeFeatureGenerator = new PlaceFeatureGenerator();
    SpecialTypeGenerator specialTypeGenerator = new SpecialTypeGenerator();
    int lastId = 0;
    public Biome lastBiome;
    public Biome newBiome;
    public static String[] weatherTypes = {"sunny", "stormy", "rainy","foggy"};
    public static String [] sceneryElements = {"a river", "a small cave", "many ancient signs on the ground", "a ruined castle", "strangely twisted statues"};
    public static Biome[] outsideBiomes = {desert,mountain,hill,forest,swamp,meadow};
    public PlaceGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"cheerful","strange","dark","foreboding","quiet"});
        this.adjectiveTypes = Arrays.asList(featureAdjective);
        this.exclusives = null;
    }


    public MapPlace getEntity(MapPlace lastPlace, int [] location, Biome biome) {
        if(biome == null) {
            if (lastPlace != null) {
                biome = biomeGenerator(lastPlace);
            }
            else{
                biome = Biome.values()[Roller.pickNumberFrom(Biome.values().length - 1)];
            }
        }
        MapPlace place = this.getBaseByType(biome, location);
        place = this.finalizeEntity(place);
        place.description = DescriptionGenerator.generate(place);
        return place;
    }


    @Override
    public MapPlace getEntity() {
        return getEntity(null,Runner.startLocation, null);
    }

    @Override
    public List<MapPlace> getEntities(int probability) {
        List<MapPlace> world = new ArrayList<MapPlace>();
        for(int i=0; i<=100; i++){
            world.add(this.getEntity());
        }
        return world;
    }



    public Biome biomeGenerator (Place place) {

       if (place == null){
            this.newBiome = outsideBiomes[Roller.pickNumberFrom(outsideBiomes.length)];
        }
       else {
           this.lastBiome = place.biome;
           int index = Arrays.binarySearch(outsideBiomes, lastBiome);
           double roll = Math.random()*100;

           if (roll <50) {
               this.newBiome = lastBiome;
           }

           if (roll > 50 && roll < 75 && index != outsideBiomes.length - 1){
               this.newBiome = outsideBiomes[index + 1];
           }
           if(roll >= 75 && index != 0) {
               this.newBiome = outsideBiomes[index - 1];
           }
           if(roll > 50 && roll < 75 && index == outsideBiomes.length - 1) {
               this.newBiome = outsideBiomes[0];
           }
           if(roll >= 75 && index == 0) {
               this.newBiome = outsideBiomes[outsideBiomes.length-1];
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

        private MapPlace getBaseByType(Biome biome, int [] location) {
        String name = biome.toString();
        switch(biome) {
            case desert:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(desert));
            case mountain:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(mountain));
            case hill:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(hill));
            case forest:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(forest));
            case meadow:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(meadow));
            case swamp:
                return new MapPlace(lastId++, biome, name,null,null, monsterGenerator.getEntities(50, biome, location),itemGenerator.getEntities(30),placeFeatureGenerator.getEntity(swamp));
            case shop:
                return new MapPlace(lastId++, biome, name,null,null,specialTypeGenerator.shopMonsterGenerator(biome, location), itemGenerator.getEntities(80),null );
            case smithy:
                return new MapPlace(lastId++, biome, name,null,null,specialTypeGenerator.shopMonsterGenerator(biome, location), null,null );
            case cave:
                return new MapPlace(lastId++, biome, name,null,null,monsterGenerator.getEntities(50, biome, location), itemGenerator.getEntities(40),null );
        }
        return null;
    }

}
