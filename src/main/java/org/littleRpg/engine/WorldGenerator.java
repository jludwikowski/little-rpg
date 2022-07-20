package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.List;

public class WorldGenerator {

    public static Place[] generateWorld(){

        Place[] world = new Place[100];
        List<Monster> empty = new ArrayList<>();

        List<Item> itemsList = new ArrayList<Item>();
        itemsList.add(itemOnTheGround());
        itemsList.add(weaponOnTheGround());

        world[7] = new Place(7, "The path of the dead","No one has ever seen a living human on this dark path.", getExits(world, new int[]{}) , empty);
        world[6] = new Place(6, "Red forest swamps","The vast and mysterious swamps leave a mark on anyone who passes through it.", getExits(world,new int[]{7}) , empty);
        world[5] = new Place(5, "The edge of the red forest swamps","The swamps hide many secrets and legends, but no one ever returned from here.", getExits(world,new int[]{6}) , getMonsters(2,MonsterTypes.goblin));
        world[4] = new Place(4, "Big thicket of the red Forest","Thicket is really dark and scary.", getExits(world, new int[]{5}) , empty);
        world[3] = new Place(3, "Clearing in the red Forest","This is a clearing in the red Forest. Small bushes can hide surprises.", getExits(world, new int[]{4}), empty, itemsList);
        world[2] = new Place(2, "Path is red Forest","This is an egde of the forest. Trees have large red leaves and are covered with golden sap.", getExits(world, new int[]{3}) , getMonsters(1,MonsterTypes.slime));
        world[1] = new Place(1, "Blue meadow edge","This is edge of the meadow new red scary forest", getExits(world,new int[]{2}) , empty);
        world[0] = new Place(0, "Blue meadow","You are in the meadow with tall bluish grass", getExits(world,new int[]{1}), empty);

        return world;
    }

    private static List<Place> getExits(Place[] world, int[] exits){
        List<Place> result = new ArrayList<Place>();
        if(exits!=null) {
            for (int i = 0; i < exits.length; i++) {
                result.add(world[exits[i]]);
            }
        }
        return result;
    }
    private static List<Monster> getMonsters(int howMany,MonsterTypes type){
        List<Monster> result = new ArrayList<Monster>();
        for(int i=0;i<howMany;i++){
            result.add(getMonster(type));
        }
        return result;
    }

    private static Monster getMonster(MonsterTypes mosterType) {
        Monster monster = new Monster();

        switch(mosterType) {
            case goblin:
                monster.name = "goblin " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 5;
                monster.currentHp = 5;
                monster.strength = 3;
                monster.attack = 30;
                monster.description = "Small green ugly goblin";
                addGoblinBaseWeapon(monster);
                break;
            case slime:
                monster.name = "slime " + String.valueOf(Math.floor(Math.random()*1000));
                monster.maxHp = 20;
                monster.currentHp = 20;
                monster.strength = 1;
                monster.attack = 15;
                monster.description = "Small foul smelling gelatinous mass";
                break;
            default:
                return null;
        }
        return monster;
    }

    private static void addGoblinBaseWeapon(Monster goblin1) {
        Weapon stick = new Weapon();
        stick.name = "Stick";
        stick.weight = 1;
        stick.baseDamageValue = 0;
        stick.bonusAttack = 2;
        goblin1.mainWeapon = stick;
    }

    private static Item itemOnTheGround() {
        Item shield = new Item();
        shield.name = "Shield";
        shield.weight = 1;
        shield.description = "Basic wooden shield";
        return shield;

    }

    private static Weapon weaponOnTheGround() {
        Weapon stick = new Weapon();
        stick.name = "Stick";
        stick.weight = 1;
        stick.baseDamageValue = 0;
        stick.bonusAttack = 2;
        stick.description = "Basic wooden stick";
        return stick;

    }

}
