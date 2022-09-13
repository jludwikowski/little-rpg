package org.littleRpg.engine;

import org.littleRpg.model.Biome;
import org.littleRpg.model.Human;
import org.littleRpg.model.MapPlace;
import org.littleRpg.model.Place;
import org.littleRpg.generator.WorldGenerator;
import org.littleRpg.generator.PlaceGenerator;

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




        System.out.println("If you want print the Map input 666");
        try {
            String mapPrint = keyboard.nextLine();
            int idMap = Integer.parseInt(mapPrint);
            if (idMap == 666) {
                mapPrinter(world, player.location);
            }
        }catch (Exception e) {
            System.out.println("Not an correct!");
        }

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
