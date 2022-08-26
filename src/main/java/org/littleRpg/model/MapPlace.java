package org.littleRpg.model;

import java.util.List;

public class MapPlace extends Place {

    int cordX;
    int cordY;
    int cordZ;

    public MapPlace(int id, String shortDescription, String description, List<Place> exits, List<Monster>  monsters, List<Item> items) {
        super(id, shortDescription, description, exits, monsters, items);
    }

}
