package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;

import java.util.Arrays;

import static org.littleRpg.model.Biome.shop;
import static org.littleRpg.model.Biome.smithy;



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
                MapPlace place = placeGenerator.getEntity(lastPlace, actualLocation,null);
                if(place.placeFeature != null && place.placeFeature.biome != null){
                    if (place.placeFeature.biome == shop) {
                        if (Roller.pickNumberFrom(100) < 10) {
                            place.placeFeature.biome = smithy;
                        }
                    }
                    this.world[i + 1][j][k] = placeGenerator.getEntity(null,actualLocation, place.placeFeature.biome);
                }
                lastPlace = place;
                this.world[i][j][k] = place;
            }
        }
        return this.world;
    }

    private static Place createStartLocation(Place location) {
        location.monsters = null;
        location.items.add(new Weapon("sword","old sword",2,5,2,false, false, Arrays.asList(WearSlot.mainHand),10,1));
        return location;
    }


}
