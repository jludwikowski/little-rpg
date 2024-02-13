package org.littleRpg.model;

import java.util.ArrayList;
import java.util.List;

public class Item extends GameEntity {
    public double weight;
    public ItemTypes type;
    public Effect effect;
    public List<WearSlot> wearSlots = new ArrayList<>();
    public int price;


    public Item(String name, ItemTypes type, String description, double weight, Effect effect, List<WearSlot> wearSlots, int price) {
        super(name, description);
        this.price = price;
        this.weight = weight;
        this.type = type;
        this.effect = effect;
        this.wearSlots = wearSlots;
    }

    public Item(String name, ItemTypes type, String description, double weight, int price) {
        super(name, description);
        this.weight = weight;
        this.type = type;
        this.price = price;
    }

}