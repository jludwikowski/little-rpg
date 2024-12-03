package org.littleRpg.engine;
import org.junit.jupiter.api.*;
import org.littleRpg.generator.MonsterGenerator;
import org.littleRpg.generator.PlaceGenerator;
import org.littleRpg.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.littleRpg.model.Biome.hill;
import static org.littleRpg.model.Monster.*;

public class RunnerTest {
    SkillManager skillManager = new SkillManager();
    List<Skill> skillList = new ArrayList<>();
    public List<Skill> prepareSkillsList(){
        Skill skill = skillManager.findSkillByName("Rage");
        skillList.add(skill);
        return skillList;
    }


    Human player = new Human("","player",20,20,20,20, 0,
            0,0, null,null, new ArrayList<Item>(),
            prepareSkillsList(),100, null,0,0,
            null, 0, new HashMap<>());
    Actions underTest = new Actions(player);
    Place [][][] testWorld = new MapPlace[2][20][20];
    public MonsterGenerator monsterGenerator =  new MonsterGenerator();
    MapPlace basicPlace = new MapPlace(1, Biome.meadow, "basic", "basic", null,null, null, null);
    MapPlace northPlace = new MapPlace(1, Biome.desert, "northDesert", "northDesert", null,null, null, null);
    MapPlace eastPlace = new MapPlace(1, Biome.mountain, "eastMountain", "eastMountain", null,null, null, null);

    MapPlace westPlace = new MapPlace(1, hill, "westHill", "westHill", null, monsterGenerator.getEntities(50, Biome.hill, new int[]{0,5,6}), null, null);
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

            testWorld[0][1][1] = basicPlace;
            testWorld[0][18][18] = basicPlace;
            testWorld[0][19][18] = southPlace;
            testWorld[0][18][19] = westPlace;
            testWorld[0][0][1] = northPlace;
            testWorld[0][1][0] = eastPlace;
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

        @Test
        public void shouldMoveLastWestPlaceByWord() {
            player.location = new int[]{0, 18, 18};
            underTest.playerMove("west", testWorld);
            assertTrue(player.location[2] == 19);
            underTest.playerMove("west", testWorld);
            assertTrue(player.location[2] == 19);

        }
        @Test
        public void shouldMoveLastEastPlaceByWord() {
            player.location = new int[]{0, 1, 1};
            underTest.playerMove("east", testWorld);
            assertTrue(player.location[2] == 0);
            underTest.playerMove("east", testWorld);
            assertTrue(player.location[2] == 0);

        }
        @Test
        public void shouldMoveLastNorthPlaceByWord() {
            player.location = new int[]{0, 1, 1};
            underTest.playerMove("north", testWorld);
            assertTrue(player.location[1] == 0);
            underTest.playerMove("north", testWorld);
            assertTrue(player.location[1] == 0);

        }
        @Test
        public void shouldMoveLastSouthPlaceByWord() {
            player.location = new int[]{0, 18, 18};
            underTest.playerMove("south", testWorld);
            assertTrue(player.location[1] == 19);
            underTest.playerMove("south", testWorld);
            assertTrue(player.location[1] == 19);

        }
    /*    @Test
        public void turnCounterTest()
        {
            int actualLenght = skillList.get(0).activationLength;
            underTest.playerMove("west", testWorld);
            player
        }

        @Test
        public void exaustedMoveTest(){

        }*/
    }
}
