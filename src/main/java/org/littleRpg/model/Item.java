package org.littleRpg.model;

public class Item extends GameEntity{
    public double weight;
    public ItemTypes type;
    public String effect;
    public boolean attackEffect;
    public boolean defenseEffect;
    public int power;

    public Item(String name, ItemTypes type, String description, double weight, String effect) {
        super(name, description);
        this.weight = weight;
        this.type = type;
        this.effect = effect;
    }

    public Item(String name, ItemTypes type, String description, double weight) {
        super(name, description);
        this.weight = weight;
        this.type = type;

    }


}
