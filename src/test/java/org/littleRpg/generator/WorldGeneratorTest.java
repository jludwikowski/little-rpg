package org.littleRpg.generator;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.generator.MonsterGenerator;
import org.littleRpg.generator.PlaceGenerator;
import org.littleRpg.generator.WorldGenerator;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldGeneratorTest {
    WorldGenerator worldGenerator= new WorldGenerator();

    @Test
    public void everyPlaceOnTheMapIsNotNullOnGroundLevelTest(){
        worldGenerator.generateWorld();
        for(int j=0;j<worldGenerator.MAX_Y;j++) {
            for(int k=0;k<worldGenerator.MAX_X;k++) {
                assertTrue(worldGenerator.world[0][j][k] != null);
            }
        }

    }
}
