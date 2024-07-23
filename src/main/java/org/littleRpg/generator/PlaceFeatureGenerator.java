package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.*;

public class PlaceFeatureGenerator extends Generator<PlaceFeature>{
    public static Map<Biome, List<PlaceFeatureType>> biomeFeatureTypes = new HashMap<>();
    Random random= new Random();
    ItemGenerator itemGenerator = new ItemGenerator();

    public PlaceFeatureGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","destroyed"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;

        biomeFeatureTypes.put(Biome.desert,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.forest,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.mountain,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.meadow,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.shop,new ArrayList<>());
        biomeFeatureTypes.put(Biome.hill,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.swamp,Arrays.asList(PlaceFeatureType.tree,PlaceFeatureType.cave,PlaceFeatureType.ruins,PlaceFeatureType.walls,PlaceFeatureType.shop, PlaceFeatureType.smithy));
        biomeFeatureTypes.put(Biome.smithy,new ArrayList<>());
        biomeFeatureTypes.put(Biome.cave,new ArrayList<>());

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
        List<Item> items = itemGenerator.getEntities(70);
        if(type == PlaceFeatureType.shop || type == PlaceFeatureType.cave || type == PlaceFeatureType.smithy){
            items = null;
        }
        Biome biome = null;
        if(Arrays.stream(Biome.values()).anyMatch(biomeValue -> biomeValue.toString().equalsIgnoreCase(type.toString()))){
            biome = Biome.valueOf(type.toString());
        }
        return new PlaceFeature(name, type.toString(), type, items, biome);
    }

 /*   public Item searchPlaceFeature(PlaceFeature placeFeature, int probability){
            Item foundedItem = null;
            if(Math.random()*100 < probability){
                foundedItem = itemGenerator.getEntity();
            }
                 return foundedItem;
    }*/


}
