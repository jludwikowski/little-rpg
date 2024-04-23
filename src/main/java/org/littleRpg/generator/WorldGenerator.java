package org.littleRpg.generator;

import org.littleRpg.model.*;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import static org.littleRpg.model.PlaceArchitectureTypes.smallBuilding;

//import static org.littleRpg.model.PlaceArchitectureTypes.shop;

public class WorldGenerator {
    MapPlace lastPlace =  null;
    PlaceGenerator placeGenerator = new PlaceGenerator();
    MapPlace[][][] world = new MapPlace[MAX_Z][MAX_Y][MAX_X];
    static int MAX_Z = 2;
    static int MAX_Y = 20;
    static int MAX_X = 20;
    public MapPlace[][][] generateWorld(){
        int i=0;
        for(int j=0;j<MAX_Y;j++) {
            for(int k=0;k<MAX_X;k++) {
                int [] actualLocation = {i,j,k};
                MapPlace place = placeGenerator.getEntity(lastPlace, actualLocation);
                if(place.placeArchitectures != null){
                    if(place.placeArchitectures.stream().anyMatch(p -> p.type == smallBuilding)){
                        MapPlace shop = placeGenerator.getShop(actualLocation);
                        this.world[i+1][j][k] = shop;
                    }
                }
                lastPlace = place;
                this.world[i][j][k] = place;
            }
        }
        return this.world;
    }

    private static Place createStartLocation(Place location) {
        location.monsters = null;
        location.items.add(new Weapon("sword","old sword",2,5,2,false, false, Arrays.asList(WearSlot.mainHand),10));
        return location;
    }


}
