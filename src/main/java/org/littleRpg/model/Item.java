package org.littleRpg.model;

public class Item extends GameEntity{
    public double weight;

    public Item(String name,String description, double weight) {
        super(name, description);
        this.weight = weight;
    }

}
