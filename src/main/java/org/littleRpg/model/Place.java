package org.littleRpg.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Place extends GameEntity {
    public int id;
    public String shortDescription;
    public List<Place> exits;
    public List<Monster> monsters;
    public List<Item> items;


    public Place(int id, String shortDescription, String description, List<Place> exits, List<Monster>  monsters, List<Item> items) {
        super(shortDescription,description);
        this.id = id;
        this.description = description;
        this.exits = exits;
        this.monsters = monsters;
        this.shortDescription = shortDescription;
        this.items = items;
    }

     public void describeLocation() {
        System.out.println(this.description);
        ListIterator<Monster> i = this.monsters.listIterator();

        while(i.hasNext()) {
            Monster nextMonster = i.next();
            System.out.println("Here is standing: " + nextMonster.description);
        }

        if (items != null) {
            ListIterator<Item> k = this.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                System.out.println("Here you can find: " + nextItem.description);

            }
        }

        if(this.exits!= null) {
            ListIterator<Place> j = this.exits.listIterator();
            while (j.hasNext()) {
                Place exitLocation = j.next();
                System.out.println("You can go to: " + String.valueOf(exitLocation.id) + " - " + exitLocation.shortDescription);
            }
        }

    }

}
