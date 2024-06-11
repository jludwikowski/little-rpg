package org.littleRpg.model;

import java.util.ArrayList;
import java.util.List;

public class Item extends GameEntity {
    public double weight;
    public ItemTypes type;
    public Effect effect;
    public List<WearSlot> wearSlots = new ArrayList<>();
    public int price;
    public int itemLevel;


    public Item(String name, ItemTypes type, String description, double weight, Effect effect, List<WearSlot> wearSlots, int price, int itemLevel) {
        this(name, type,description, weight,price);
        this.effect = effect;
        this.wearSlots = wearSlots;
    }

    public Item(String name, ItemTypes type, String description, double weight, int price) {
        super(name, description);
        this.weight = weight;
        this.type = type;
        this.price = price;
    }

    public void upgradeItemLevel(){
        System.out.println("This item cannot be upgradable");
    }

}