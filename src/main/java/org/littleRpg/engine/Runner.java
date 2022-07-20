package org.littleRpg.engine;

import org.littleRpg.model.Human;
import org.littleRpg.model.Monster;
import org.littleRpg.model.Place;
import org.littleRpg.engine.WorldGenerator;
import org.littleRpg.engine.Judge;

import java.util.ListIterator;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Place[] world = WorldGenerator.generateWorld();

        Human player = new Human();
        player.maxHp = 30;
        player.currentHp = player.maxHp;
        player.strength = 4;
        player.attack = 40;

        System.out.println("What is you name?");
        Scanner keyboard = new Scanner(System.in);
        player.name = keyboard.nextLine();
        player.location = world[0].id;

        while(player.currentHp >= 0){
            player.location = locationActions(world, player, keyboard);
        }
        System.out.println("GAME OVER");

    }

    public static int locationActions(Place[] world, Human player, Scanner keyboard) {
        Place location = world[player.location];
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
            } else {
                Place placePlayerChose = world[id];
                if (location.exits.contains(placePlayerChose)) {
                    return placePlayerChose.id;
                } else {
                    System.out.println("Not an right exit!");
                }
            }
        } catch (Exception e) {
            System.out.println("Not an exit!");
        }
        return player.location;
    }



}
