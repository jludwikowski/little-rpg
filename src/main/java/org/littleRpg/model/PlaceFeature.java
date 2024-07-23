package org.littleRpg.model;

import java.util.List;

public class PlaceFeature extends GameEntity{
    public Biome biome;
    public PlaceFeatureType type;
    public List<Item> hiddenItems;
    public PlaceFeature(String name, String description, PlaceFeatureType placeFeatureType, List<Item> hiddenItems, Biome biome) {
        super(name, description);
        this.type = placeFeatureType;
        this.hiddenItems = hiddenItems;
        this.biome = biome;
    }
}
