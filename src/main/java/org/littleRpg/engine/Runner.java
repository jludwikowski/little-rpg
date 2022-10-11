package org.littleRpg.engine;

import org.littleRpg.model.*;
import org.littleRpg.generator.WorldGenerator;


import java.util.ArrayList;
import java.util.Scanner;

public class Runner {


    public static void main(String[] args) {

        WorldGenerator worldGenerator = new WorldGenerator();
        MapPlace[][][] world = worldGenerator.generateWorld();

        Human player = new Human("player","player",100,30,90,10,null,null, new ArrayList<Item>());

        System.out.println("What is you name?");
        Scanner keyboard = new Scanner(System.in);
        player.name = keyboard.nextLine();
        player.location = new int[]{0,5,5};

        while(player.currentHp >= 0){
            player.location = locationActions(world, player, keyboard);
        }

        System.out.println("GAME OVER");

    }


    public static void mapPrinter(Place[][][] world, int[] location) {

        for (int i=0; i < world.length; i++){
            for (int j=0; j < world[0].length; j++){

                for (int k=0; k < world[0][0].length; k++){
                    Biome biome = world[i][j][k].biome;
                    if (location[0]==i && location[1]==j && location[2]==k){
                        System.out.print("00");
                    }else {
                        switch (biome) {
                            case desert:
                                System.out.print("~~");
                                break;
                            case mountain:
                                System.out.print("^^");
                                break;
                            case hill:
                                System.out.print("hh");
                                break;
                            case forest:
                                System.out.print("##");
                                break;
                            case meadow:
                                System.out.print("mm");
                                break;
                            case swamp:
                                System.out.print("ss");
                                break;
                        }
                    }
                }
                System.out.print("\n");
            }

        }


    }

    public static int[] locationActions(Place[][][] world, Human player, Scanner keyboard) {
        Place location = world[player.location[0]][player.location[1]][player.location[2]];
        location.describeLocation();

        System.out.println("If you want print the Map press m");

        System.out.println("What Do you do?");

        if(location.items != null && !location.items.isEmpty()) {
            System.out.println("If you want pick up Items press p");
        }

        if(!location.monsters.isEmpty()) {
            System.out.println("You encountered monster!!! press a to attack");
        }
        try {
            String command = keyboard.nextLine();
            switch (command) {
                case "stats":
                    System.out.println(player.getStats());
                case "n":
                    if (player.location[1] > 0) {
                        player.location[1] = player.location[1] - 1;
                    }
                    break;
                case "p":
                    player.pickUpItems(location.items);
                    location.items.clear();
                    break;
                case "s":
                    if (player.location[1] < 19) {
                        player.location[1] = player.location[1] + 1;
                    }
                    break;
                case "e":
                    if (player.location[2] > 0) {
                        player.location[2] = player.location[2] - 1;
                    }
                    break;
                case "w":
                    if (player.location[2] < 19) {
                        player.location[2] = player.location[2] + 1;
                    }
                    break;
                case "m":
                    mapPrinter(world, player.location);
                    break;
                case "a":
                    if (!location.monsters.isEmpty()) {
                        location.monsters = Judge.combat(player, location);
                    }
                    break;
                case "l":
                    player.showItems(player.loot);
                    break;

                default:
                    System.out.println("i don't recognize this try again");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return player.location;
    }





}
