package org.littleRpg.engine;

import org.littleRpg.model.*;
import org.littleRpg.generator.WorldGenerator;


import java.util.ArrayList;
import java.util.ListIterator;
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

        world[0][5][5].items.add(new Weapon("stick", "stick", 0 , 0, 0, false));
        Place location = world[player.location[0]][player.location[1]][player.location[2]];
        System.out.println(location.getDescription());
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
        Place thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
        //System.out.println(thisPlace.getDescription());
        //player.thisPlace = new int[]{0,5,5};
        

        System.out.println("What Do you do?");
        if(!thisPlace.monsters.isEmpty()) {
            System.out.println("You encountered monster!!! press a to attack");
        }
        try {
            String command = keyboard.nextLine();
            switch (command) {
                case "stats":
                    System.out.println(player.getStats());
                break;
                case "north":
                    if (player.location[1] > 0) {
                        player.location[1] = player.location[1] - 1;
                        thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                        System.out.println(thisPlace.getDescription());
                    }
                    break;
                case "pickup":
                    if (!thisPlace.monsters.isEmpty()) {
                        thisPlace.monsters = Judge.monsterAttack(player, thisPlace);
                    }
                    player.pickUpItems(thisPlace.items);
                    thisPlace.items.clear();
                    break;
                case "south":
                    if (player.location[1] < 19) {
                        player.location[1] = player.location[1] + 1;
                        thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                        System.out.println(thisPlace.getDescription());
                    }
                    break;
                case "help":
                    System.out.println("stats - player stats \n\nnorth - move to north \nsouth - move to south\n" +
                            "east - move to east\nwest - move to west\n\npickup - pickup items\natack - atack for monster\n" + "special - special atack for monster\n" +
                            "loot - show player items\nmyitems - show equip items\nwear - show wear items\n" +
                            "monsteritems - show monster items\nmap - print map\ncheckmonster - check monster items");
                    break;
                case "east":
                    if (player.location[2] > 0) {
                        player.location[2] = player.location[2] - 1;
                        thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                        System.out.println(thisPlace.getDescription());
                    }
                    break;
                case "west":
                    if (player.location[2] < 19) {
                        player.location[2] = player.location[2] + 1;
                        thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                        System.out.println(thisPlace.getDescription());
                    }
                    break;
                case "map":
                    mapPrinter(world, player.location);
                    break;
                case "attack":
                    if (!thisPlace.monsters.isEmpty()) {
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0);
                    }
                    break;

                case "loot":
                    player.showItems(player.loot);
                    break;
                case "myitems":
                    player.showEquipItems(player.mainWeapon, player.armor);
                    break;
                case "special":
                    if (!thisPlace.monsters.isEmpty() && player.mainWeapon.isRanged) {
                        thisPlace.monsters = Judge.specialCombat(player, thisPlace);
                    }
                    break;
                case "wear":
                    if (!thisPlace.monsters.isEmpty()) {
                        thisPlace.monsters = Judge.monsterAttack(player, thisPlace);
                    }
                    player.wear();
                    break;
                case "checkmonster":
                    if (!thisPlace.monsters.isEmpty()) {
                        ListIterator<Monster> o = thisPlace.monsters.listIterator();
                        while(o.hasNext()) {
                            Monster nextMonster = o.next();
                            System.out.println(nextMonster.description);
                            Monster.showEquipItems(nextMonster.mainWeapon, nextMonster.armor);
                            System.out.println("~~~~~~~~~~~~~~");
                        }
                    }
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
