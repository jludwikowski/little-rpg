package org.littleRpg.engine;

import org.littleRpg.generator.PlaceGenerator;
import org.littleRpg.generator.TextColorGenerator;
import org.littleRpg.model.*;
import org.littleRpg.generator.WorldGenerator;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

import static org.littleRpg.engine.Actions.TURNCOUNTERCOMMANDS;
import static org.littleRpg.model.PlaceArchitectureTypes.shop;

public class Runner {

    public static void main(String[] args) {

        WorldGenerator worldGenerator = new WorldGenerator();
        PlaceGenerator placeGenerator = new PlaceGenerator();
        MapPlace[][][] world = worldGenerator.generateWorld();
        Scanner keyboard = new Scanner(System.in);
        Human player = new Human("","player",20,20,20,20, 0,0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>(),100, null,0,0, null, 0);
        Actions actions = new Actions(player);
        while("".equalsIgnoreCase(player.name)){
            System.out.println("What is you name?");
            player.name = keyboard.nextLine();
        }
        player.chooseRace();
        player.chooseClass();
        player.location = new int[]{0,5,5};

        Place location = prepareStartingLocation(world, player, placeGenerator);

        System.out.println(location.getDescription());
        while(player.currentHp >= 0){
            player.location = actions.locationActions(world, keyboard);
        }

        System.out.println("GAME OVER");

    }

    private static Place prepareStartingLocation(MapPlace[][][] world, Human player, PlaceGenerator placeGenerator) {
        world[0][5][5].items.add(new Weapon("stick", "stick", 0 , 0, 0, false, false, Arrays.asList(WearSlot.mainHand), 10));
        world[0][5][5].items.add(new Weapon("sword", "sword", 0 , 0, 0, false, false, Arrays.asList(WearSlot.mainHand), 10));
        world[0][5][5].items.add(new Weapon("bow", "bow", 0 , 0, 0, true, true, Arrays.asList(WearSlot.mainHand, WearSlot.offHand), 10));
        Place location = world[player.location[0]][player.location[1]][player.location[2]];
        world[player.location[0]+1][player.location[1]][player.location[2]] = placeGenerator.getShop();
        location.placeArchitectures.add(new PlaceArchitecture(shop, "u Zdzicha", "wodka, nalewki"));
        location.items.add(new Scroll("StoneDefend",ItemTypes.scroll, "StoneDefend",0, null,"StoneDefend",10));
        location.items.add(new Armor("shield", "shield", 5,2, Arrays.asList(WearSlot.offHand), 10));
        location.items.add(new Scroll("Thunderbolt", ItemTypes.scroll, "Thunderbolt", 0.1, null,"Thunderbolt",10));
        location.items.add(new Item("ancientPower", ItemTypes.ring, "ancientPower", 0.1, new Effect("Strength boost",7,EffectType.buff,Attribute.strength,9999999),Arrays.asList(WearSlot.finger), 5));
        return location;
    }


}
