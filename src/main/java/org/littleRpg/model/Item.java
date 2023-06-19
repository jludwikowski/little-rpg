package org.littleRpg.model;

public class Item extends GameEntity{
    public double weight;
    public ItemTypes type;
    public Effect effect;
    public WearSlot slot;

    public Item(String name, ItemTypes type, String description, double weight, Effect effect, WearSlot slot) {
        super(name, description);
        this.weight = weight;
        this.type = type;
        this.effect = effect;
        this.slot = slot;
    }

    public Item(String name, ItemTypes type, String description, double weight) {
        super(name, description);
        this.weight = weight;
        this.type = type;
    }


}
