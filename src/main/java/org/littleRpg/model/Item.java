package org.littleRpg.model;

public class Item extends GameEntity{
    public double weight;
    public ItemTypes type;

    public Item(String name, ItemTypes type, String description, double weight) {
        super(name, description);
        this.weight = weight;
        this.type = type;
    }

}
