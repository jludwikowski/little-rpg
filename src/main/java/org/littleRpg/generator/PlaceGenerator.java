package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.littleRpg.model.Biome.shop;


public class PlaceGenerator extends Generator<MapPlace> {


    MonsterGenerator monsterGenerator = new MonsterGenerator();
    ItemGenerator itemGenerator = new ItemGenerator();
    WeaponGenerator weaponGenerator = new WeaponGenerator();
    ArmorGenerator armorGenerator = new ArmorGenerator();
    PlaceArchitectureGenerator placeArchitectureGenerator = new PlaceArchitectureGenerator();
    SpecialTypeGenerator specialTypeGenerator = new SpecialTypeGenerator();
    int lastId = 0;
    public Biome lastBiome;
    public Biome newBiome;


    public PlaceGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"cheerful","strange","dark","foreboding","quiet"});
        this.adjectiveTypes = Arrays.asList(featureAdjective);
        this.exclusives = null;
    }

    public MapPlace getShop(){
        Biome biome = shop;
        return this.getBaseByType(biome);
    }


    @Override
    public MapPlace getEntity() {
        Biome biome = Biome.values()[Roller.pickNumberFrom(Biome.values().length-1)];
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


    public MapPlace getEntity(MapPlace lastPlace) {
        Biome biome = biomeGenerator(lastPlace);
        MapPlace place = this.getBaseByType(biome);
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





    private MapPlace getBaseByType(Biome biome) {
        String name = biome.toString();
        switch(biome) {
            case desert:
                return new MapPlace(lastId++, biome, name,"scorching empty desert",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case mountain:
                return new MapPlace(lastId++, biome, name,"empty windy high mountains",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case hill:
                return new MapPlace(lastId++, biome, name,"hills",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case forest:
                return new MapPlace(lastId++, biome, name,"dense forest",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case meadow:
                return new MapPlace(lastId++, biome, name,"long grass meadow",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case swamp:
                return new MapPlace(lastId++, biome, name,"treacherous swamp",null, monsterGenerator.getEntities(50, biome),itemGenerator.getEntities(30),placeArchitectureGenerator.getEntities(50));
            case shop:
                return new MapPlace(lastId++, biome, name,"item Shop",null,Arrays.asList(specialTypeGenerator.shopkeeperGenerator(Biome.shop)), itemGenerator.getEntities(80),null );
        }
        return null;
    }

}
