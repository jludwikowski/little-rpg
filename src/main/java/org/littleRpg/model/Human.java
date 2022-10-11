package org.littleRpg.model;

import java.util.List;
import java.util.ListIterator;

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



}
