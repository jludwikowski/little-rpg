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

    public Place(){
        this.id = 999;
    }
    public Place(int id, String shortDescription, String description, List<Place> exits, List<Monster>  monsters) {
        this.id = id;
        this.description = description;
        this.exits = exits;
        this.monsters = monsters;
        this.shortDescription = shortDescription;
    }

    public Place(int id, String shortDescription, String description, List<Place> exits, List<Monster>  monsters, List<Item> items) {
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
        ListIterator<Place> j = this.exits.listIterator();

        while(i.hasNext()) {
            Monster nextMonster = i.next();
            System.out.println("Here is standing: " + nextMonster.description);
        }

        if (items != null) {
            ListIterator<Item> l = this.items.listIterator();
            while (l.hasNext()) {
                Item nextItem = l.next();
                System.out.println("Here you can find: " + nextItem.description);

            }
        }

        while(j.hasNext()) {
            Place exitLocation = j.next();
            System.out.println("You can go to: " + String.valueOf(exitLocation.id) + " - " + exitLocation.shortDescription);
        }


    }

}
