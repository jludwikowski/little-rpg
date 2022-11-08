package org.littleRpg.model;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Human extends Monster{

    public int gamerId;
    public int[] location;
    //public Item items;
    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot) {
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



    public void showItems(List<Item> items ) {
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
        System.out.println("Wybierz item który chcesz zalozyc");
        Scanner scanner = new Scanner(System.in);
        int itemIndex = scanner.nextInt();
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


}
