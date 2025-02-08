package org.littleRpg.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.model.Human;
import org.littleRpg.model.Item;
import org.littleRpg.model.Skill;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class HumanTest {

    public List<Item> itemsOntheGround = new ArrayList<>();
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUpChooseClass() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out; // Zachowanie oryginalnego PrintStream
        System.setOut(new PrintStream(outputStream)); // Przechwytywanie System.out
    }

    @Test
    public void testChooseClassValidChoice() {
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        ByteArrayInputStream input = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(input);
       // Mockito.doReturn(0).when(player).readChoice(Mockito.any());// Mockowanie metody readChoice, by zawsze zwracała wybór 0
        player.chooseClass();
        //doNothing().when(player).adjust(any(Human.class));// Mockowanie metody adjust, aby nic nie robiła
        String expectedOutput = "mage"; // Oczekiwany wynik wypisania klasy
        System.out.println(player.className.toString());
        assertTrue(player.className == PlayerClasses.mage);
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    /*@Test
    public void testChooseClassInvalidChoice() {
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        ByteArrayInputStream input = new ByteArrayInputStream(("j" + System.getProperty("line.separator")+"0"+ System.getProperty("line.separator")).getBytes());
        System.setIn(input);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        player.chooseClass();
        String expectedOutput = "i don't recognize this, try again"; // Oczekiwany wynik wypisania klasy
        assertTrue(outputStream.toString().contains(expectedOutput));
    }*/


    @Test
    public void getCorrectStatsTest(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        player.type = MonsterTypes.human;
        String expectedStats = "maxHp: 20.0\n" +
                "currentHP: 20.0\n" +
                "actualHp: 100%\n" +
                "[****************************************************************************************************]\n" +
                "actualMana: 100%\n" +
                "attack: 0\n" +
                "strength: 0\n" +
                "damageReduction: 0\n" +
                "goldCoins: 100\n" +
                "exp: 0\n" +
                "thirst: 0/100\n" +
                "hunger: 0/100\n" +
                "race human";


        String actualStats = player.getStats();
        assertEquals(expectedStats, actualStats);

    }

    @Test
    public void getCorrectStatsTest50PercentHp(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        player.type = MonsterTypes.human;
        player.currentHp = 10;
        String expectedStats = "maxHp: 20.0\n" +
                "currentHP: 10.0\n" +
                "actualHp: 50%\n" +
                "[**************************************************--------------------------------------------------]\n" +
                "actualMana: 100%\n" +
                "attack: 0\n" +
                "strength: 0\n" +
                "damageReduction: 0\n" +
                "goldCoins: 100\n" +
                "exp: 0\n" +
                "thirst: 0/100\n" +
                "hunger: 0/100\n" +
                "race human";


        String actualStats = player.getStats();
        assertEquals(expectedStats, actualStats);

    }

    @Test
    public void getCorrectGetPercent(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        assertTrue(player.getPercentStats(100,100) == 100)  ;
    }

    @Test
    public void getIncorrectGetPercent(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        assertTrue(player.getPercentStats(100,0) == 0)  ;
    }

    @Test
    public void correctPickupItem(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        Weapon stick = new Weapon("stick", "stick", 0 , 0, 0,
                false, false, Arrays.asList(WearSlot.mainHand), 10,1);
        itemsOntheGround.add(stick);
        assertTrue(player.loot.isEmpty());
        player.pickUpItems(itemsOntheGround);
        assertTrue(player.loot.get(0) == stick);
        assertTrue(player.loot.contains(stick));  //drugi sposób assercji - sprawdzenie czy zawiera dokładnie ten obiekt
        assertTrue(itemsOntheGround.isEmpty());
    }

    @Test
    public void pickupItemOnEmptyList(){
        Human player = new Human("","player",20,20,20,20, 0,
                0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),
                100, null,0,0, null, 0, new HashMap<>());
        assertTrue(player.loot.isEmpty());
        player.pickUpItems(itemsOntheGround);
        assertTrue(player.loot.isEmpty());

    }


}
