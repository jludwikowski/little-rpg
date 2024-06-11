package org.littleRpg.model;

public class PlaceFeature extends GameEntity{
    public PlaceFeatureType type;
    public PlaceFeature(String name, String description, PlaceFeatureType placeFeatureType) {
        super(name, description);
        this.type = placeFeatureType;
    }
}
