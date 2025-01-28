package org.littleRpg.generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
}
