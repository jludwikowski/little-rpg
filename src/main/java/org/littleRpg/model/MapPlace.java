package org.littleRpg.model;

import java.util.List;

public class MapPlace extends Place {

    int cordX;
    int cordY;
    int cordZ;

    public MapPlace(int id, Biome biome, String shortDescription, String description, List<Place> exits, List<Monster>  monsters, List<Item> items, List<PlaceFeature> placeFeature) {
        super(id, biome, shortDescription, description, exits, monsters, items, placeFeature);
    }

}
