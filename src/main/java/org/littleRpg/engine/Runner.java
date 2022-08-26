package org.littleRpg.engine;

import org.littleRpg.model.Human;
import org.littleRpg.model.MapPlace;
import org.littleRpg.model.Place;
import org.littleRpg.generator.WorldGenerator;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        WorldGenerator worldGenerator = new WorldGenerator();
        MapPlace[][][] world = worldGenerator.generateWorld();

        Human player = new Human("player","player",30,30,40,4,null,null,null);

        System.out.println("What is you name?");
        Scanner keyboard = new Scanner(System.in);
        player.name = keyboard.nextLine();
        player.location = new int[]{0,5,5};

        while(player.currentHp >= 0){
            player.location = locationActions(world, player, keyboard);
        }
        System.out.println("GAME OVER");

    }

    public static int[] locationActions(Place[][][] world, Human player, Scanner keyboard) {
        Place location = world[player.location[0]][player.location[1]][player.location[2]];
        location.describeLocation();

        System.out.println("What Do you do?");
        if(!location.monsters.isEmpty()) {
            System.out.println("You encountered monster!!! 999 to attack");
        }

        try {
            String action = keyboard.nextLine();
            int id = Integer.parseInt(action);

            if(id == 999 && !location.monsters.isEmpty()) {
                location.monsters = Judge.combat(player, location.monsters);
            } /*else {
                Place placePlayerChose = world[id];
                if (location.exits.contains(placePlayerChose)) {
                    return placePlayerChose.id;
                } else {
                    System.out.println("Not an right exit!");
                }
            }*/
        } catch (Exception e) {
            System.out.println("Not an exit!");
        }
        return player.location;
    }



}
