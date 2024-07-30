package org.littleRpg.generator;

import org.littleRpg.engine.EffectManager;
import org.littleRpg.engine.Roller;
import org.littleRpg.engine.SkillManager;
import org.littleRpg.model.*;

import java.util.Arrays;

public class ItemGenerator extends Generator<Item>{

    SkillManager skillManager;
    EffectManager effectManager;

    public ItemGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(70, new String[] {"ornate","sturdy","ugly","strange","exceptional","elf-crafted"});
        AdjectivesTable timeAdjective = new AdjectivesTable(60, new String[] {"old","weathered","new","ancient"});
        this.skillManager = new SkillManager();
        this.adjectiveTypes = Arrays.asList(timeAdjective, featureAdjective);
        this.exclusives = null;
        this.effectManager = new EffectManager();
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
                entity.price = new Double(Math.floor(entity.price * 1.4)).intValue();
                break;
            case "sturdy":
                entity.weight = new Double(Math.floor(entity.weight * 1.2)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1.2)).intValue();
                break;
            case "ugly":
                entity.weight = new Double(Math.floor(entity.weight * 1)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1.1)).intValue();
                break;
            case "strange":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1.2)).intValue();
                break;
            case "exceptional":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1.8)).intValue();
                break;
            case "elf-crafted":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1.6)).intValue();
                break;
            case "old":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.price = new Double(Math.floor(entity.price * 0.7)).intValue();
                break;
            case "weathered":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.price = new Double(Math.floor(entity.price * 0.8)).intValue();
                break;
            case "new":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.price = new Double(Math.floor(entity.price * 1)).intValue();
                break;
            case "ancient":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                entity.price = new Double(Math.floor(entity.price * 0.6)).intValue();
                break;
        }
        return entity;
    }



    private Item getBaseByType(ItemTypes type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(type) {
            case bottleOfWater:
                Item.ItemBuilder bottleOfWater = new Item.ItemBuilder(name, type, type.toString());
                bottleOfWater.setWeight(0.8);
                bottleOfWater.setPrice(3);
                return bottleOfWater.build();
            case emptyBottle:
                Item.ItemBuilder emptyBottle = new Item.ItemBuilder(name, type, type.toString());
                emptyBottle.setWeight(0.2);
                emptyBottle.setPrice(1);
                return emptyBottle.build();
            case cup:
                Item.ItemBuilder cup = new Item.ItemBuilder(name, type, type.toString());
                cup.setWeight(0.5);
                cup.setPrice(1);
                return cup.build();
            case skull:
                Item.ItemBuilder skull = new Item.ItemBuilder(name, type, type.toString());
                skull.setWeight(1.5);
                skull.setPrice(1);
                return skull.build();
            case bone:
                Item.ItemBuilder bone = new Item.ItemBuilder(name, type, type.toString());
                bone.setWeight(1.1);
                bone.setPrice(1);
                return bone.build();
            case hide:
                Item.ItemBuilder hide = new Item.ItemBuilder(name, type, type.toString());
                hide.setWeight(3.1);
                hide.setPrice(3);
                return hide.build();
            case meat:
                Item.ItemBuilder meat = new Item.ItemBuilder(name, type, type.toString());
                meat.setWeight(1.3);
                meat.setPrice(2);
                return meat.build();
            case cookedMeat:
                Item.ItemBuilder cookedMeat = new Item.ItemBuilder(name, type, type.toString());
                cookedMeat.setWeight(1.3);
                cookedMeat.setPrice(4);
                return cookedMeat.build();
            case flint:
                Item.ItemBuilder flint = new Item.ItemBuilder(name, type, type.toString());
                flint.setWeight(0.3);
                flint.setPrice(3);
                return flint.build();
            case scroll:
                Item.ItemBuilder scroll = new Item.ItemBuilder(name, type, type.toString());
                scroll.setWeight(0.1);
                scroll.setPrice(10);
                scroll.setEffect(effectManager.getRandomEffect());
                scroll.setItemLevel(1);
                return scroll.build();
            case necklace:
                Item.ItemBuilder necklace = new Item.ItemBuilder(name, type, type.toString());
                necklace.setWeight(0.3);
                necklace.setPrice(9);
                necklace.setEffect(effectManager.getRandomEffect());
                necklace.setWearSlots(Arrays.asList(WearSlot.neck));
                necklace.setItemLevel(1);
                return necklace.build();
            case ring:
                Item.ItemBuilder ring = new Item.ItemBuilder(name, type, type.toString());
                ring.setWeight(0.2);
                ring.setPrice(7);
                ring.setEffect(effectManager.getRandomEffect());
                ring.setWearSlots(Arrays.asList(WearSlot.finger));
                ring.setItemLevel(1);
                return ring.build();
        }
        return null;
    }


}
