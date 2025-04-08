package org.littleRpg.generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.model.Place;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldGeneratorTest {
    WorldGenerator worldGenerator;
    @BeforeEach
    public void setup(){
        worldGenerator= new WorldGenerator();
    }
    @Test
    public void everyPlaceOnTheMapIsNotNullOnGroundLevelTest(){
        worldGenerator.generateWorld();
        for(int j=0;j<worldGenerator.MAX_Y;j++) {
            for(int k=0;k<worldGenerator.MAX_X;k++) {
                assertNotNull(worldGenerator.world[0][j][k]);
            }
        }

    }

    @Test
    void onlySpecialPlacesExistInSecondLayer() {
        Place[][][] world = worldGenerator.generateWorld();

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                Place upper = world[1][y][x];
                if (upper != null) {
                    assertNotNull(upper.placeFeature, "world[1][" + y + "][" + x + "] should have a placeFeature");
                }
            }
        }
    }

    @Test
    void everyPlaceHasAssignedBiome() {
        Place[][][] world = worldGenerator.generateWorld();

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                Place p = world[0][y][x];
                assertNotNull(p.biome, "Biome should not be null at world[0][" + y + "][" + x + "]");
            }
        }
    }

    @Test
    void atLeastOnePlaceHasPlaceFeature() {
        Place[][][] world = worldGenerator.generateWorld();

        boolean found = false;
        for (int y = 0; y < 20 && !found; y++) {
            for (int x = 0; x < 20 && !found; x++) {
                if (world[0][y][x].placeFeature != null) {
                    found = true;
                }
            }
        }

        assertTrue(found, "At least one place in world[0] should have a placeFeature");
    }

}
