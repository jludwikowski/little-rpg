package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.*;

public class PlaceFeatureGenerator extends Generator<PlaceFeature>{
    public static Map<Biome, List<PlaceFeatureType>> biomeFeatureTypes = new HashMap<>();

    public PlaceFeatureGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","destroyed"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;

        biomeFeatureTypes.put(Biome.desert,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.forest,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.mountain,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.meadow,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.shop,new ArrayList<>());
        biomeFeatureTypes.put(Biome.hill,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.swamp,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.smallBuilding));
        biomeFeatureTypes.put(Biome.smithy,new ArrayList<>());

    }


    @Override
    public PlaceFeature getEntity() {
        PlaceFeatureType type = PlaceFeatureType.values()[Roller.pickNumberFrom(PlaceFeatureType.values().length)];
        PlaceFeature placeFeature = this.getBaseByType(type);
        placeFeature = this.finalizeEntity(placeFeature);
        return placeFeature;
    }

    public PlaceFeature getEntity(Biome biome){
        List <PlaceFeatureType> biomeFeatureTypeList = biomeFeatureTypes.get(biome);
        PlaceFeatureType type = biomeFeatureTypeList.get(Roller.pickNumberFrom(biomeFeatureTypeList.size()));
        PlaceFeature placeFeature = this.getBaseByType(type);
        placeFeature = this.finalizeEntity(placeFeature);
        return placeFeature;
    }



    @Override
    public PlaceFeature adjust(PlaceFeature entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;
        return entity;
    }



    private PlaceFeature getBaseByType(PlaceFeatureType type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        return new PlaceFeature(name, type.toString(), type);
    }
}
