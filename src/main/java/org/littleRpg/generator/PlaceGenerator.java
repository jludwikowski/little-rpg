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
    public List<MapPlace> getEntities(int probability) {
        List<MapPlace> world = new ArrayList<MapPlace>();
        for(int i=0; i<=100; i++){
            world.add(this.getEntity());
        }
        return world;
    }

    @Override
    public MapPlace getEntity() {
        Biome biome = Biome.values()[Roller.pickNumberFrom(MonsterTypes.values().length)];
        MapPlace place = this.getBaseByType(biome);
        place = this.finalizeEntity(place);
        return place;
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
                return new MapPlace(lastId++, name,"scorching empty desert",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case mountain:
                return new MapPlace(lastId++, name,"empty windy high mountains",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case hill:
                return new MapPlace(lastId++, name,"hills",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case forest:
                return new MapPlace(lastId++, name,"dense forest",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case meadow:
                return new MapPlace(lastId++, name,"long grass meadow",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
            case swamp:
                return new MapPlace(lastId++, name,"treacherous swamp",null, monsterGenerator.getEntities(50),itemGenerator.getEntities(30));
        }
        return null;
    }

}
