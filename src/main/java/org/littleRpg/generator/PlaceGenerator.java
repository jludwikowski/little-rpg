package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlaceGenerator extends Generator<MapPlace> {

    MonsterGenerator monsterGenerator = new MonsterGenerator();
    ItemGenerator itemGenerator = new ItemGenerator();
    int lastId = 0;



    public PlaceGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"cheerful","strange","dark","foreboding","quiet"});
        this.adjectiveTypes = Arrays.asList(featureAdjective);
        this.exclusives = null;
    }


    @Override
    public MapPlace getEntity() {
        Biome biome = Biome.values()[Roller.pickNumberFrom(Biome.values().length)];
        MapPlace place = this.getBaseByType(biome);
        place = this.finalizeEntity(place);
        return place;
    }

    @Override
    public List<MapPlace> getEntities(int probability) {
        List<MapPlace> world = new ArrayList<MapPlace>();
        for(int i=0; i<=100; i++){
            world.add(this.getEntity());
        }
        return world;
    }


    public MapPlace getEntity(MapPlace lastPlaceX, MapPlace lastPlaceY) {
        Biome biome = biomeGenerator(lastPlaceX, lastPlaceY);
        MapPlace place = this.getBaseByType(biome);
        place = this.finalizeEntity(place);
        return place;
    }

    public Biome biomeGenerator (Place placeX, Place placeY) {
        Biome newBiome = null;
        Biome lastBiomeX;
        Biome lastBiomeY;

       if (placeX == null && placeY == null){
            newBiome = Biome.values()[Roller.pickNumberFrom(Biome.values().length)];
        }
       else {
           if(placeX != null) {
               lastBiomeX = placeX.biome;
               int index1 = Arrays.binarySearch(Biome.values(), lastBiomeX);

               double roll = Math.random()*100;

               if (roll <50) {
                   newBiome = lastBiomeX;
               }

               if (roll > 50 && roll < 75 && index1 != Biome.values().length - 1){
                   newBiome = Biome.values()[index1 + 1];
               }
               if(roll >= 75 && index1 != 0) {
                   newBiome = Biome.values()[index1 - 1];
               }
               if(roll > 50 && roll < 75 && index1 == Biome.values().length - 1) {
                   newBiome = Biome.values()[0];
               }
               if(roll >= 75 && index1 == 0) {
                   newBiome = Biome.values()[Biome.values().length-1];
               }

           }
           if(placeY != null){
               lastBiomeY = placeY.biome;
               int index2 = Arrays.binarySearch(Biome.values(), lastBiomeY);
               double roll = Math.random()*100;

               if (roll <50) {
                   newBiome = lastBiomeY;
               }

               if (roll > 50 && roll < 75 && index2 != Biome.values().length - 1){
                   newBiome = Biome.values()[index2 + 1];
               }
               if(roll >= 75 && index2 != 0) {
                   newBiome = Biome.values()[index2 - 1];
               }
               if(roll > 50 && roll < 75 && index2 == Biome.values().length - 1) {
                   newBiome = Biome.values()[0];
               }
               if(roll >= 75 && index2 == 0) {
                   newBiome = Biome.values()[Biome.values().length-1];
               }

           }

           if(placeX != null && placeY != null) {
               double roll = Math.random() * 100;
               lastBiomeX = placeX.biome;
               int index1 = Arrays.binarySearch(Biome.values(), lastBiomeX);
               lastBiomeY = placeY.biome;
               int index2 = Arrays.binarySearch(Biome.values(), lastBiomeY);

               if (roll <= 30) {
                   newBiome = lastBiomeX;
               }
               if (roll > 30 && roll <= 60) {
                   newBiome = lastBiomeY;
               }

               if (roll > 60 && roll <= 70 && index1 != Biome.values().length - 1) {
                   newBiome = Biome.values()[index1 + 1];
               }
               if (roll > 70 && roll <= 80 && index2 != Biome.values().length - 1) {
                   newBiome = Biome.values()[index2 + 1];
               }
               if (roll > 80 && roll <= 90 && index1 != 0) {
                   newBiome = Biome.values()[index1 - 1];
               }
               if (roll > 90 && roll <= 100 && index2 != 0) {
                   newBiome = Biome.values()[index2 - 1];
               }

           }

       }
        return newBiome;
    }




    @Override
    public MapPlace adjust(MapPlace entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;
        return entity;
    }

    private MapPlace getBaseByType(Biome biome) {
        String name = biome.toString();
        switch(biome) {
            case desert:
                return new MapPlace(lastId++, biome, name,"scorching empty desert",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case mountain:
                return new MapPlace(lastId++, biome, name,"empty windy high mountains",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case hill:
                return new MapPlace(lastId++, biome, name,"hills",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case forest:
                return new MapPlace(lastId++, biome, name,"dense forest",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case meadow:
                return new MapPlace(lastId++, biome, name,"long grass meadow",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case swamp:
                return new MapPlace(lastId++, biome, name,"treacherous swamp",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
        }
        return null;
    }

}
