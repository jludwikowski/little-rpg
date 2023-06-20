package org.littleRpg.engine;

import org.littleRpg.generator.TextColorGenerator;
import org.littleRpg.model.*;
import org.littleRpg.generator.WorldGenerator;



import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        WorldGenerator worldGenerator = new WorldGenerator();
        MapPlace[][][] world = worldGenerator.generateWorld();

        Human player = new Human("player","player",20,20,20,20, 0,0,0, null,null, new ArrayList<Item>(), new ArrayList<Skill>());
        System.out.println("What is you name?");
        Scanner keyboard = new Scanner(System.in);
        player.name = keyboard.nextLine();
        player.chooseRace();
        player.chooseClass();
        player.location = new int[]{0,5,5};

        world[0][5][5].items.add(new Weapon("stick", "stick", 0 , 0, 0, false));
        Place location = world[player.location[0]][player.location[1]][player.location[2]];
        location.items.add(new Scroll("StoneDefend",ItemTypes.scroll, "StoneDefend",0, null,"StoneDefend"));
        location.items.add(new Armor("shield", "shield", 5,2));
        location.items.add(new Scroll("Thunderbolt", ItemTypes.scroll, "Thunderbolt", 0.1, null,"Thunderbolt"));
        location.items.add(new Item("ancientPower", ItemTypes.ring, "ancientPower", 0.1, new Effect("Strength boost",7,EffectType.buff,Attribute.strength,9999999),WearSlot.finger));

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
                    if (!player.isExausted()) {
                        player.effectTurnCounter();
                        if (player.location[1] > 0) {
                            player.location[1] = player.location[1] - 1;
                            thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                            System.out.println(thisPlace.getDescription());
                            player.timePasses();
                        }
                    }
                    else {
                        TextColorGenerator.purpleText("If you want move drink water");
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
                    if (!player.isExausted()) {
                        player.effectTurnCounter();
                        if (player.location[1] < 19) {
                            player.location[1] = player.location[1] + 1;
                            thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                            System.out.println(thisPlace.getDescription());
                        }
                    }
                    break;
                case "help":
                    System.out.println("stats - player stats \n\nnorth - move to north \nsouth - move to south\n" +
                            "east - move to east\nwest - move to west\n\npickup - pickup items\natack - atack for monster\n" + "special - special atack for monster\n" +
                            "loot - show player items\nmyitems - show equip items\nwear - show wear items\n" +
                            "monsteritems - show monster items\nmap - print map\ncheckmonster - check monster items");
                    break;
                case "east":
                    if (!player.isExausted()) {
                        player.effectTurnCounter();
                        if (player.location[2] > 0) {
                            player.location[2] = player.location[2] - 1;
                            thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                            System.out.println(thisPlace.getDescription());
                        }
                    }
                    break;
                case "west":
                    if (!player.isExausted()) {
                        player.effectTurnCounter();

                        if (player.location[2] < 19) {
                            player.location[2] = player.location[2] + 1;
                            thisPlace = world[player.location[0]][player.location[1]][player.location[2]];
                            System.out.println(thisPlace.getDescription());
                        }
                    }
                    break;
                case "map":
                    mapPrinter(world, player.location);
                    break;
                case "attack":

                    if (!thisPlace.monsters.isEmpty()) {
                        player.effectTurnCounter();
                        thisPlace.monsters = Judge.combat(player, thisPlace, 0, null);
                    }
                    break;

                case "loot":
                    ListHelper.showList("In loot you have: ",player.loot);
                    break;
                case "myitems":
                    player.showEquipItems(player.mainWeapon, player.armor);
                    break;
                case "special":
                    if (!thisPlace.monsters.isEmpty() && player.mainWeapon.isRanged) {
                        player.effectTurnCounter();
                        thisPlace.monsters = Judge.rangeAttack(player, thisPlace, null);
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
               case "save":
                    LoadSaveOperator.savePoint(player);
                    break;
               case "load":
                    player = LoadSaveOperator.loadPoint();
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
