package org.littleRpg.engine;

import org.littleRpg.generator.ItemGenerator;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.List;
import org.littleRpg.generator.MonsterGenerator;
import org.littleRpg.generator.ArmorGenerator;
import org.littleRpg.generator.WeaponGenerator;

public class WorldGenerator {

    public static Place[] generateWorld(){

        Place[] world = new Place[100];
        List<Monster> empty = new ArrayList<>();

        List<Item> itemsList = new ArrayList<Item>();
        List<Armor> armorsList = new ArrayList<>();
        List<Weapon> weaponsList = new ArrayList<>();
        itemsList.add(itemOnTheGround());
        itemsList.add(weaponOnTheGround());

        world[7] = new Place(7, "The path of the dead","No one has ever seen a living human on this dark path.", getExits(world, new int[]{}) , generateMonsters(50), generateItems(80), generateArmor(80), generateWeapon(90));
        world[6] = new Place(6, "Red forest swamps","The vast and mysterious swamps leave a mark on anyone who passes through it.", getExits(world,new int[]{7}) , generateMonsters(50),generateItems(80), generateArmor(80), generateWeapon(90));
        world[5] = new Place(5, "The edge of the red forest swamps","The swamps hide many secrets and legends, but no one ever returned from here.", getExits(world,new int[]{6}) , generateMonsters(50),generateItems(80), generateArmor(80), generateWeapon(90));
        world[4] = new Place(4, "Big thicket of the red Forest","Thicket is really dark and scary.", getExits(world, new int[]{5}) , generateMonsters(50),generateItems(80), generateArmor(80), generateWeapon(90));
        world[3] = new Place(3, "Clearing in the red Forest","This is a clearing in the red Forest. Small bushes can hide surprises.", getExits(world, new int[]{4}), generateMonsters(50), generateItems(80), generateArmor(80), generateWeapon(90));
        world[2] = new Place(2, "Path is red Forest","This is an egde of the forest. Trees have large red leaves and are covered with golden sap.", getExits(world, new int[]{3}) , generateMonsters(50),generateItems(80), generateArmor(80), generateWeapon(90));
        world[1] = new Place(1, "Blue meadow edge","This is edge of the meadow new red scary forest", getExits(world,new int[]{2}) , generateMonsters(50),generateItems(80), generateArmor(80), generateWeapon(90));
        world[0] = new Place(0, "Blue meadow","You are in the meadow with tall bluish grass", getExits(world,new int[]{1}), empty, generateItems(70), generateArmor(80), generateWeapon(90));

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

    private static List<Monster> generateMonsters(int probability) {
        MonsterGenerator monsterGenerator = new MonsterGenerator();
        return monsterGenerator.getEntities(probability);
    }

    private static List<Item> generateItems(int probability) {
        ItemGenerator itemGenerator = new ItemGenerator();
        return itemGenerator.getEntities(probability);
    }
    private static List<Armor> generateArmor(int probability) {
        ArmorGenerator armorGenerator = new ArmorGenerator();
        return armorGenerator.getEntities(probability);
    }
    private static List<Weapon> generateWeapon(int probability) {
        WeaponGenerator weaponGenerator = new WeaponGenerator();
        return weaponGenerator.getEntities(probability);
    }

    private static Item itemOnTheGround() {
        return new Item("Shield","Basic wooden shield",5);
    }

    private static Weapon weaponOnTheGround() {
        return new Weapon("Stick","Basic wooden stick",1,2,0);
    }

}
