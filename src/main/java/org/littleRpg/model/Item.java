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

    public Item(ItemBuilder builder){
        super(builder.name, builder.description);
        this.effect = effect;
        this.wearSlots = wearSlots;
        this.weight = weight;
        this.type = type;
        this.price = price;
        this.itemLevel = itemLevel;
    }

    public void upgradeItemLevel(){
        System.out.println("This item cannot be upgradable");
    }

    public static class ItemBuilder{
        private double weight;
        private ItemTypes type;
        private Effect effect;
        private List<WearSlot> wearSlots = new ArrayList<>();
        private int price;
        private int itemLevel;
        private String name;
        private String description;

        public ItemBuilder(String name, ItemTypes type, String description){
            this.name = name;
            this.description = description;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public void setType(ItemTypes type) {
            this.type = type;
        }

        public void setEffect(Effect effect) {
            this.effect = effect;
        }

        public void setWearSlots(List<WearSlot> wearSlots) {
            this.wearSlots = wearSlots;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setItemLevel(int itemLevel) {
            this.itemLevel = itemLevel;
        }

        public Item build(){
            return new Item(this);
        }

    }

}