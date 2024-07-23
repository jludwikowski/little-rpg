package org.littleRpg.engine;

import org.littleRpg.generator.TextColorGenerator;
import org.littleRpg.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

//import static org.littleRpg.model.PlaceArchitectureTypes.shop;

public class Actions {
    public static final List<String> TRAVELCOMMANDS = Arrays.asList("north", "south", "east", "west","enter", "exit");
    public static final List<String> TURNCOUNTERCOMMANDS = Arrays.asList("pickup", "attack", "takeoff", "special", "wear",
            "buy", "sell", "useitem","learn","useskill","drop");
    public static final List<String> STATICCOMMANDS = Arrays.asList("stats", "help", "map", "loot", "myitems", "checkmonster",
            "save","load");
    public Human player;

    public Actions(Human player){
        this.player = player;
    }

    public void turnCounter(Place place){
        List<Monster> aggressiveMonsters = place.getAggressiveMonsters();
        if (!aggressiveMonsters.isEmpty()) {
            player.effectTurnCounter();
            Judge.monsterAttack(player, place);
        }
    }

    public int[] locationActions(Place[][][] world, Scanner keyboard) {
        Place thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
        System.out.println("What Do you do?");
        if(thisPlace.monsters != null && !thisPlace.monsters.isEmpty()) {
            System.out.println("You encountered monster!!! press a to attack");
        }
        try {
            String command = keyboard.nextLine();
            switch (command) {
                case "stats":
                    System.out.println(player.getStats());
                    break;
                case "north":
                case "n":
                case "enter":
                case "exit":
                case "east":
                case "e":
                case "west":
                case "w":
                case "south":
                case "s":
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
                case "attack":
                    System.out.println("test");
                    if (!thisPlace.monsters.isEmpty()) {
                        System.out.println("test2");
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null);
                    }
                    break;
                case "exchange":
                    if(player.attributePoints > 0){
                        player.attributePointsExchange();
                    }else {
                        System.out.println("You don't have enough points");
                    }
                    break;
                case "loot":
                    ListHelper.showList("In loot you have: ",player.loot,false);
                    break;
                case "takeoff":
                    player.takeOff();
                    break;
                case "myitems":
                    player.showEquipItems();
                    break;
                case "special":
                    if (!thisPlace.monsters.isEmpty() && ((Weapon) player.equippedItems.get(WearSlot.mainHand)).isRanged) {
                        thisPlace.monsters = Judge.rangeAttack(player, thisPlace, null);
                    }
                    break;
                case "wear":
                    player.wear();
                    break;
                case "checkmonster":
                    if (!thisPlace.monsters.isEmpty()) {
                        ListIterator<Monster> o = thisPlace.monsters.listIterator();
                        while(o.hasNext()) {
                            Monster nextMonster = o.next();
                            System.out.println(nextMonster.description);
                            nextMonster.showEquipItems();
                            System.out.println("~~~~~~~~~~~~~~");
                        }
                    }
                    break;

                case "buy":
                    if(thisPlace.biome == Biome.shop){
                        Monster seller = thisPlace.monsters.get(0);
                        LivingEntity.tradeItem(seller, player, "buy");
                    }
                    break;
                case "sell":
                    if(thisPlace.biome == Biome.shop){
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
                        player.useSkill(player, thisPlace);
                    }
                    break;
                case "search":
                    if(thisPlace.placeFeature != null){
                        System.out.println("You started searching through " + thisPlace.placeFeature.type);
                        player.pickUpItems(thisPlace.placeFeature.hiddenItems);
                    }else{
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
            if(TURNCOUNTERCOMMANDS.contains(command)){
                turnCounter(thisPlace);
            }
            if(TRAVELCOMMANDS.contains(command)){
                player.effectTurnCounter();

            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return player.location;
    }

    public static void mapPrinter(Place[][][] world, int[] location) {

        for (int i=0; i < world.length; i++){
            for (int j=0; j < world[0].length; j++){

                for (int k=0; k < world[0][0].length; k++){
                    Biome biome = world[i][j][k].biome;
                    if (location[0]==i && location[1]==j && location[2]==k){
                        TextColorGenerator.purpleText("00");
                    }else {
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
                        }
                    }
                }
                System.out.print("\n");
            }

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
