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
        String describeLocation = "You are in: \n" + this.description + "\n";
        if(placeArchitectures != null) {
            describeLocation += "\nIn this place you have: \n";
            ListIterator<PlaceArchitecture> j = this.placeArchitectures.listIterator();
            while (j.hasNext()) {
                PlaceArchitecture nextPlaceArchitecture = j.next();
                describeLocation += "-" + nextPlaceArchitecture.getDescription() + " - " +
                        nextPlaceArchitecture.type.toString() +  "\n";
            }
        }
        if(monsters != null && !monsters.isEmpty()){
            describeLocation += "\nHere is standing: \n";
            ListIterator<Monster> i = this.monsters.listIterator();
            while(i.hasNext()) {
                Monster nextMonster = i.next();
                describeLocation +=  "-" + nextMonster.getDescription() +"\n";
            }
        }

        if (items != null && biome != Biome.shop && !items.isEmpty()) {
            describeLocation += "\nHere you can find items: \n";
            ListIterator<Item> k = this.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                describeLocation += "-" + nextItem.getDescription() + "\n";

            }
        }
        if (items != null && biome == Biome.shop && !items.isEmpty()) {
            describeLocation += "\nHere you can buy: \n";
            ListIterator<Item> k = this.items.listIterator();
            while (k.hasNext()) {
                Item nextItem = k.next();
                describeLocation +=  "-" + nextItem.getDescription() + "\n";

            }
        }


        return describeLocation;
    }

    public List<Monster> getAggressiveMonsters(){
        return this.monsters.stream().filter(monster -> monster.aggressive).collect(Collectors.toList());
    }

}
