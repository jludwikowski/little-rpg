package org.littleRpg.engine;
import org.junit.jupiter.api.*;

import org.littleRpg.model.*;

import java.util.ArrayList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RunnerTest {
    Human player = new Human("","player",20,20,20,20, 0,0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),100, null,0,0, null, 0, new HashMap<>());
    Actions underTest = new Actions(player);
    Place [][][] testWorld = new MapPlace[2][20][20];
    MapPlace basicPlace = new MapPlace(1, Biome.meadow, "basic", "basic", null,null, null, null);
    MapPlace northPlace = new MapPlace(1, Biome.desert, "northDesert", "northDesert", null,null, null, null);
    MapPlace eastPlace = new MapPlace(1, Biome.mountain, "eastMountain", "eastMountain", null,null, null, null);
    MapPlace westPlace = new MapPlace(1, Biome.hill, "westHill", "westHill", null,null, null, null);
    MapPlace southPlace = new MapPlace(1, Biome.meadow, "southMeadow", "southMeadow", null,null, null, null);
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_METHOD)
    public class MovementTests {
        @BeforeEach
        public void setup() {

            player.location = new int[]{0, 5, 5};
            testWorld[0][5][5] = basicPlace;
            testWorld[0][4][5] = northPlace;
            testWorld[0][6][5] = southPlace;
            testWorld[0][5][4] = eastPlace;
            testWorld[0][5][6] = westPlace;
        }

        @Test
        public void shouldMoveNorthByLetter() {
            underTest.playerMove("n", testWorld);
            assertTrue(player.location[1] == 4);
        }

        @Test
        public void shouldMoveNorthByWord() {
            underTest.playerMove("north", testWorld);
            assertTrue(player.location[1] == 4);
        }

        @Test
        public void shouldMoveSouthByLetter() {
            underTest.playerMove("s", testWorld);
            assertTrue(player.location[1] == 6);
        }

        @Test
        public void shouldMoveSouthByWord() {
            underTest.playerMove("south", testWorld);
            assertTrue(player.location[1] == 6);
        }

        @Test
        public void shouldMoveEastByLetter() {
            underTest.playerMove("e", testWorld);
            assertTrue(player.location[2] == 4);
        }

        @Test
        public void shouldMoveEastByWord() {
            underTest.playerMove("east", testWorld);
            assertTrue(player.location[2] == 4);
        }

        @Test

        public void shouldMoveWestByLetter() {
            underTest.playerMove("w", testWorld);
            assertTrue(player.location[2] == 6);
        }

        @Test
        public void shouldMoveWestByWord() {
            underTest.playerMove("west", testWorld);
            assertTrue(player.location[2] == 6);
        }

    }
}
