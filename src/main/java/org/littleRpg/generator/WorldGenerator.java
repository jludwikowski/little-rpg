package org.littleRpg.generator;

import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {
    PlaceGenerator placeGenerator = new PlaceGenerator();
    MapPlace[][][] world = new MapPlace[MAX_Z][MAX_Y][MAX_X];
    static int MAX_Z = 1;
    static int MAX_Y = 20;
    static int MAX_X = 20;
    public MapPlace[][][] generateWorld(){
        MapPlace place =  null;
        MapPlace placeX;
        MapPlace placeY;
        for(int i=0;i<MAX_Z;i++) {
            for(int j=0;j<MAX_Y;j++) {
                for(int k=0;k<MAX_X;k++) {
                    placeX = j>0 ? this.world[i][j-1][k] : null;
                    placeY = k>0 ? this.world[i][j][k-1] : null;
                    place = placeGenerator.getEntity(placeX, placeY);

                    this.world[i][j][k] = place;
                }
            }
        }
        return this.world;
    }

    private static Place createStartLocation(Place location) {
        location.monsters = null;
        location.items.add(new Weapon("sword","old sword",2,5,2,false));
        return location;
    }


}
