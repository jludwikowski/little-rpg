package org.littleRpg.model;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Place extends GameEntity {

    public int id;
    public String shortDescription;
    public List<Place> exits;
    public List<Monster> monsters;
    public List<Item> items;
    public List<PlaceArchitecture> placeArchitectures;
    public Biome biome;



    public Place(int id, Biome biome, String shortDescription, String description, List<Place> exits, List<Monster> monsters, List<Item> items, List<PlaceArchitecture> placeArchitectures) {
        super(shortDescription,description);
        this.id = id;
        this.biome = biome;
        this.description = description;
        this.exits = exits;
        this.monsters = monsters;
        this.shortDescription = shortDescription;
        this.items = items;
        this.placeArchitectures = placeArchitectures;

    }

    public String getDescription() {
        String describeLocation = this.description + "\n" + "In this place you can visit " + "\n";
        if(placeArchitectures != null) {
            ListIterator<PlaceArchitecture> j = this.placeArchitectures.listIterator();
            while (j.hasNext()) {
                PlaceArchitecture nextPlaceArchitecture = j.next();
                describeLocation += nextPlaceArchitecture.getDescription() + "\n";
            }
        }
        if(monsters != null){
        ListIterator<Monster> i = this.monsters.listIterator();
            while(i.hasNext()) {
                Monster nextMonster = i.next();
                describeLocation += "Here is standing: " + nextMonster.getDescription() +"\n";
            }
        }

        if (items != null && biome != Biome.shop) {
            ListIterator<Item> k = this.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                describeLocation += "Here you can find: " + nextItem.getDescription() + "\n";

            }
        }
        if (items != null && biome == Biome.shop) {
            ListIterator<Item> k = this.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                describeLocation += "Here you can buy: " + nextItem.getDescription() + "\n";

            }
        }


        return describeLocation;
    }

    public List<Monster> getAggressiveMonsters(){
        return this.monsters.stream().filter(monster -> monster.aggressive).collect(Collectors.toList());
    }

}
