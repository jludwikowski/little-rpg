package org.littleRpg.engine;

import org.littleRpg.generator.TextColorGenerator;
import org.littleRpg.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//import static org.littleRpg.model.PlaceArchitectureTypes.shop;

public class Actions {
    public static final List<String> TRAVELCOMMANDS = List.of("north", "south", "east", "west", "enter", "exit");
    public static final List<String> TURNCOUNTERCOMMANDS = List.of("pickup", "attack", "takeoff", "special", "wear",
            "buy", "sell", "useitem", "learn", "useskill", "drop");
    public static final List<String> STATICCOMMANDS = List.of("stats", "help", "map", "loot", "myitems", "checkmonster",
            "save", "load");
    public Human player;
    public QuestManager questManager = new QuestManager();

    public Actions(Human player) {
        this.player = player;
    }

    public void turnCounter(Place place) {
        List<Monster> aggressiveMonsters = place.getAggressiveMonsters();
        player.effectTurnCounter();
        if (!aggressiveMonsters.isEmpty()) {
            Judge.monsterAttack(player, place);
        }
    }

    /*public int[] locationActions(Place[][][] world, Scanner keyboard) {
        Place thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
        System.out.println("What Do you do?");
        if (thisPlace.monsters != null && !thisPlace.monsters.isEmpty()) {
            System.out.println("You encountered monster!!! press a to attack");
        }
        try {
            String command = keyboard.nextLine();
            switch (command) {
                case "stats":
                    System.out.println(player.getStats());
                    break;
                case "north", "n", "enter", "exit", "east", "e","west", "w", "south", "s":
                    playerMove(command, world);
                    break;
                case "pickup":
                    player.pickUpItems(thisPlace.items);
                    thisPlace.items.clear();
                    break;
                case "help":
                    System.out.println("stats - player stats \n\nnorth - move to north \nsouth - move to south\n" +
                            "east - move to east\nwest - move to west\n\npickup - pickup items\nattack - attack for monster\n" + "special - special attack for monster\n" +
                            "loot - show player items\nmyitems - show equip items\nwear - show wear items\n" +
                            "map - print map\ncheckmonster - check monster items\ndrop - drop equiped item\n" +
                            "useitem - use item from loot \nsave - save progress\nload - load save\nlearn - learn spell for your class\n" +
                            "useskill - use learned skill");
                    break;
                case "map":
                    mapPrinter(world, player.location);
                    break;
                case "a", "attack":
                    if (!thisPlace.monsters.isEmpty()) {
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null, player);
                    }
                    break;
                case "exchange":
                    if (player.attributePoints > 0) {
                        player.attributePointsExchange();
                    } else {
                        System.out.println("You don't have enough points");
                    }
                    break;
                case "loot":
                    ListHelper.showList("In loot you have: ", player.loot, false);
                    break;
                case "takeoff":
                    player.takeOff();
                    break;
                case "myitems":
                    player.showEquipItems();
                    break;
                case "special":
                    if (!thisPlace.monsters.isEmpty() && ((Weapon) player.equippedItems.get(WearSlot.mainHand)).isRanged) {
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null, player);
                    }
                    break;
                case "wear":
                    player.wear();
                    break;
                case "startquest":
                    if (thisPlace.biome == Biome.shop || thisPlace.biome == Biome.smithy) {
                        questManager.chooseQuest(player);
                    } else {
                        System.out.println("You cannot start quest here!\n");
                    }
                    break;
                case "finishquest":
                    if (thisPlace.biome == Biome.shop || thisPlace.biome == Biome.smithy) {
                        questManager.finishQuest(player);
                    } else {
                        System.out.println("You cannot finish quest here!\n");
                    }
                    break;
                case "checkmonster":
                    if (!thisPlace.monsters.isEmpty()) {
                        ListIterator<Monster> o = thisPlace.monsters.listIterator();
                        while (o.hasNext()) {
                            Monster nextMonster = o.next();
                            System.out.println(nextMonster.description);
                            nextMonster.showEquipItems();
                            System.out.println("~~~~~~~~~~~~~~");
                        }
                    }
                    break;
                case "buy":
                    if (thisPlace.biome == Biome.shop) {
                        Monster seller = thisPlace.monsters.get(0);
                        LivingEntity.tradeItem(seller, player, "buy");
                    }
                    break;
                case "sell":
                    if (thisPlace.biome == Biome.shop) {
                        Monster seller = thisPlace.monsters.get(0);
                        LivingEntity.tradeItem(player, seller, "sell");
                    }
                    break;
                case "useitem":
                    player.useItem();
                    break;
                case "learn":
                    player.learnSkill();
                    break;
                case "useskill":
                    if (!thisPlace.monsters.isEmpty()) {
                        player.useSkill(player, thisPlace, player);
                    }
                    break;
                case "search":
                    if (thisPlace.placeFeature != null) {
                        System.out.println("You started searching through " + thisPlace.placeFeature.type);
                        player.pickUpItems(thisPlace.placeFeature.hiddenItems);
                    } else {
                        System.out.println("You don't have anything to check here");
                    }
                    break;
                case "look":
                    thisPlace.getDescription();
                    break;
                case "save":
                    LoadSaveOperator.savePoint(player);
                    break;
                case "load":
                    player = LoadSaveOperator.loadPoint();
                    break;
                case "drop":
                    player.dropItems();
                    break;
                default:
                    System.out.println("i don't recognize this try again");
            }
            if (TURNCOUNTERCOMMANDS.contains(command)) {
                turnCounter(thisPlace);
            }
            if (TRAVELCOMMANDS.contains(command)) {
                player.effectTurnCounter();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
        return player.location;
    }*/



    public int[] locationActions(Place[][][] world, Scanner keyboard) {
        Place thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
        System.out.println("What do you do?");
        if (thisPlace.monsters != null && !thisPlace.monsters.isEmpty()) {
            System.out.println("You encountered a monster! Press 'a' to attack.");
        }
        try {
            String command = keyboard.nextLine();

            switch (command) {
                case "stats" -> System.out.println(player.getStats());
                case "north", "n", "enter", "exit", "east", "e", "west", "w", "south", "s" -> playerMove(command, world);
                case "pickup" -> {
                    player.pickUpItems(thisPlace.items);
                    thisPlace.items.clear();
                }
                case "help" -> System.out.println("""
                    stats - player stats
                    
                    Movement:
                    north, south, east, west, enter, exit
                    
                    Combat:
                    attack (a), special
                    
                    Inventory:
                    pickup, loot, myitems, wear, takeoff, drop
                    
                    Skills:
                    learn, useskill
                    
                    Other:
                    map, checkmonster, startquest, finishquest, useitem
                    save, load
                    """);
                case "map" -> mapPrinter(world, player.location);
                case "a", "attack" -> {
                    if (!thisPlace.monsters.isEmpty()) {
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null, player);
                    }
                }
                case "exchange" -> {
                    if (player.attributePoints > 0) {
                        player.attributePointsExchange();
                    } else {
                        System.out.println("You don't have enough points");
                    }
                }
                case "loot" -> ListHelper.showList("In loot you have: ", player.loot, false);
                case "takeoff" -> player.takeOff();
                case "myitems" -> player.showEquipItems();
                case "special" -> {
                    if (!thisPlace.monsters.isEmpty() && player.equippedItems.get(WearSlot.mainHand) instanceof Weapon weapon && weapon.isRanged) {
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null, player);
                    }
                }
                case "wear" -> player.wear();
                case "startquest" -> {
                    if (thisPlace.biome == Biome.shop || thisPlace.biome == Biome.smithy) {
                        questManager.chooseQuest(player);
                    } else {
                        System.out.println("You cannot start a quest here!");
                    }
                }
                case "finishquest" -> {
                    if (thisPlace.biome == Biome.shop || thisPlace.biome == Biome.smithy) {
                        questManager.finishQuest(player);
                    } else {
                        System.out.println("You cannot finish a quest here!");
                    }
                }
                case "checkmonster" -> {
                    for (Monster monster : thisPlace.monsters) {
                        System.out.println(monster.description);
                        monster.showEquipItems();
                        System.out.println("~~~~~~~~~~~~~~");
                    }
                }
                case "buy" -> {
                    if (thisPlace.biome == Biome.shop) {
                        Monster seller = thisPlace.monsters.get(0);
                        LivingEntity.tradeItem(seller, player, "buy");
                    }
                }
                case "sell" -> {
                    if (thisPlace.biome == Biome.shop) {
                        Monster seller = thisPlace.monsters.get(0);
                        LivingEntity.tradeItem(player, seller, "sell");
                    }
                }
                case "useitem" -> player.useItem();
                case "learn" -> player.learnSkill();
                case "useskill" -> {
                    if (!thisPlace.monsters.isEmpty()) {
                        player.useSkill(player, thisPlace, player);
                    }
                }
                case "search" -> {
                    if (thisPlace.placeFeature != null) {
                        System.out.println("You started searching through " + thisPlace.placeFeature.type);
                        player.pickUpItems(thisPlace.placeFeature.hiddenItems);
                    } else {
                        System.out.println("You don't have anything to check here");
                    }
                }
                case "look" -> thisPlace.getDescription();
                case "save" -> LoadSaveOperator.savePoint(player);
                case "load" -> player = LoadSaveOperator.loadPoint();
                case "drop" -> player.dropItems();
                default -> System.out.println("I don't recognize this. Try again.");
            }
            if (TURNCOUNTERCOMMANDS.contains(command)) {
                turnCounter(thisPlace);
            }
            if (TRAVELCOMMANDS.contains(command)) {
                player.effectTurnCounter();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error during input processing.");
        }
        return player.location;
    }

    public static void mapPrinter(Place[][][] world, int[] location) {
        int i = 0;
        for (int j = 0; j < world[i].length; j++) {
            for (int k = 0; k < world[i][j].length; k++) {
                Place place = world[i][j][k];
                Biome biome = (place != null) ? place.biome : null;
                if (location[0] == i && location[1] == j && location[2] == k) {
                    TextColorGenerator.purpleText("00");
                } else {
                    switch (biome) {
                        case desert:
                            TextColorGenerator.yellowText("~~");
                            break;
                        case mountain:
                            TextColorGenerator.whiteText("^^");
                            break;
                        case hill:
                            TextColorGenerator.greenText("hh");
                            break;
                        case forest:
                            TextColorGenerator.greenText("##");
                            break;
                        case meadow:
                            TextColorGenerator.cyanText("mm");
                            break;
                        case swamp:
                            TextColorGenerator.blueText("ss");
                            break;
                        default:
                            System.out.print("??"); // nieznane
                            break;
                    }
                }
            }
            System.out.print("\n");
        }

    }





    public void playerMove(String command, Place[][][] world){
        Place thisPlace = null;
        int [] oldLocation = Arrays.copyOf(player.location, player.location.length);
        if (!player.isExausted()) {
            try {
                switch (command) {
                    case "north":
                    case "n":
                        //thisPlace = world[player.location[0]][player.location[1]-1][player.location[2]];
                        player.location[1] = player.location[1]-1;
                        break;
                    case "south":
                    case "s":
                        //thisPlace = world[player.location[0]][player.location[1]+1][player.location[2]];
                        player.location[1] = player.location[1]+1;
                        break;
                    case "west":
                    case "w":
                        //thisPlace = world[player.location[0]][player.location[1]][player.location[2]+1];
                        player.location[2] = player.location[2]+1;
                        break;
                    case "east":
                    case "e":
                        //thisPlace = world[player.location[0]][player.location[1]][player.location[2]-1];
                        player.location[2] = player.location[2]-1;
                        break;
                    case "enter":
                        //thisPlace = world[player.location[0]+1][player.location[1]][player.location[2]];
                        player.location[0] = player.location[0]+1;
                        break;
                    case "exit":
                        //thisPlace = world[player.location[0]-1][player.location[1]][player.location[2]];
                        player.location[0] = player.location[0]-1;
                        break;
                }
                thisPlace=world[player.location[0]][player.location[1]][player.location[2]];
                System.out.println(thisPlace.getDescription());
                player.timePasses();
            } catch (ArrayIndexOutOfBoundsException |NullPointerException e) {
                System.out.println("You cannot go more to " + command);
                player.location = oldLocation;
            }
        }

    }


}
