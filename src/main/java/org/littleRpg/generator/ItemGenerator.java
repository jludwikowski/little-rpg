package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.AdjectivesTable;
import org.littleRpg.model.Item;
import org.littleRpg.model.ItemTypes;

import java.util.Arrays;

public class ItemGenerator extends Generator<Item>{

    public ItemGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(70, new String[] {"ornate","sturdy","ugly","strange","exceptional","elf-crafted"});
        AdjectivesTable timeAdjective = new AdjectivesTable(60, new String[] {"old","weathered","new","ancient"});

        this.adjectiveTypes = Arrays.asList(timeAdjective, featureAdjective);
        this.exclusives = null;
    }

    @Override
    public Item getEntity() {
        ItemTypes type = ItemTypes.values()[Roller.pickNumberFrom(ItemTypes.values().length-2)];
        Item item = this.getBaseByType(type);
        item = this.finalizeEntity(item);
        return item;
    }

    public Item adjust(Item entity, String adj) {
        if (entity != null) {
            entity.name = adj + " " + entity.name;
            entity.description = adj + " " + entity.description;
        }

        switch(adj) {
            case "ornate":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                break;
            case "sturdy":
                entity.weight = new Double(Math.floor(entity.weight * 1.2)).intValue();
                break;
            case "ugly":
                entity.weight = new Double(Math.floor(entity.weight * 1)).intValue();
                break;
            case "strange":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                break;
            case "exceptional":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                break;
            case "elf-crafted":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                break;
            case "old":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                break;
            case "weathered":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                break;
            case "new":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                break;
            case "ancient":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                break;
        }
        return entity;
    }

    private Item getBaseByType(ItemTypes type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(type) {
            case bottleOfWater:
                return new Item(name, type, type.toString(), 0.8);
            case emptyBottle:
                return new Item(name, type, type.toString(), 0.2);
            case cup:
                return new Item(name, type, type.toString(), 0.5);
            case skull:
                return new Item(name, type, type.toString(), 1.5);
            case bone:
                return new Item(name, type, type.toString(), 1.1);
            case hide:
                return new Item(name, type, type.toString(), 3.1);
            case meat:
                return new Item(name, type, type.toString(), 1.3);
        }
        return null;
    }
}
