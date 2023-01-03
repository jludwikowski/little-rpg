package org.littleRpg.model;

import org.littleRpg.generator.TextColorGenerator;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Human extends Monster{

    public int gamerId;
    public int[] location;
    public static int actualThirst;
    public static int actualSatiety;
    //public Item items;
    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot, int actualThirst) {
        super(name, description,maxHp, currentHp, attack, strength, mainWeapon, armor, loot);
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }


    public void pickUpItems(List <Item> itemsOntheGround) {
        if(itemsOntheGround != null) {
            this.loot.addAll(itemsOntheGround);
            ListIterator<Item> groundItemIterator = itemsOntheGround.listIterator();
            while (groundItemIterator.hasNext()) {
                Item nextOnTheGround = groundItemIterator.next();
                System.out.println("podniosles: " + nextOnTheGround.description);
            }
            showItems(this.loot);
        }
    }



    public static void showItems(List<Item> items ) {
        if(items != null) {
            ListIterator<Item> itemIterator = items.listIterator();
            while (itemIterator.hasNext()) {
                Item nextItem = itemIterator.next();
                System.out.println("In loot you have: " + nextItem.description);

            }
        }
    }

    public void wear() {
        showItems(loot);
        System.out.println("w tym momencie nosisz: " + mainWeapon + armor);
        int itemIndex = itemChoice("Wybierz item który chcesz zalozyc");
        Item wearItem = loot.get(itemIndex);
        if(wearItem instanceof Armor) {
            Item dropArmor = armor;
            System.out.println("ściągasz: " + dropArmor.description );
            armor = (Armor) wearItem;
            loot.remove(itemIndex);
            if(dropArmor != null) {
                loot.add(dropArmor);
            }
            System.out.println("Założyłeś: " + this.armor.description);

        }
        if(wearItem instanceof Weapon) {
            Item dropMainWeapon = mainWeapon;
            if(mainWeapon != null) {
                System.out.println("ściągasz: " + dropMainWeapon.description);
            }
            mainWeapon = (Weapon) wearItem;
            loot.remove(itemIndex);
            if(dropMainWeapon != null) {
                loot.add(dropMainWeapon);
            }
            System.out.println("Założyłeś: " + this.mainWeapon.description);
        }
        if(!(wearItem instanceof Armor) && !(wearItem instanceof Weapon)) {
            System.out.println("Nie mozesz tego uzyc!");
        }

    }

    public static int itemChoice(String prompt){
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void useItem() {
        showItems(loot);
        int itemIndex = itemChoice("Wybierz item który chcesz uzyc");
        if (loot.get(itemIndex).type == ItemTypes.bottleOfWater){
            System.out.println("wypijasz: " + loot.get(itemIndex).description );
            changeThirst(30);
            loot.remove(itemIndex);
        }

    }

    public static void changeThirst(int value) {
        TextColorGenerator. purpleText("Your actual Thirst: " + actualThirst);
        actualThirst =+ value;
        if (actualThirst > 100){
            actualThirst =100;
        }

    }
    public static void changeSatiety(int value) {
        TextColorGenerator. purpleText("Your actual Satiety: " + actualSatiety;
        actualSatiety =+ value;
        if (actualSatiety > 100){
            actualSatiety =100;
        }

    }


}
