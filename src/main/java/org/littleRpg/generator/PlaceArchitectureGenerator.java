package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.Arrays;

public class PlaceArchitectureGenerator extends Generator<PlaceArchitecture>{
    public PlaceArchitectureGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","destroyed"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;
    }


    @Override
    public PlaceArchitecture getEntity() {
        PlaceArchitectureTypes type = PlaceArchitectureTypes.values()[Roller.pickNumberFrom(PlaceArchitectureTypes.values().length)];
        PlaceArchitecture architectureType = this.getBaseByType(type);
        architectureType = this.finalizeEntity(architectureType);
        return architectureType;
    }

    @Override
    public PlaceArchitecture adjust(PlaceArchitecture entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;
        return entity;
    }



    private PlaceArchitecture getBaseByType(PlaceArchitectureTypes type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        return new PlaceArchitecture(type, name, type.toString());
    }
}
