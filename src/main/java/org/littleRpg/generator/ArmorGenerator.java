package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.*;


import java.util.*;

public class ArmorGenerator extends Generator<Armor> {


    public ArmorGenerator() {
        AdjectivesTable featureAdjective = new AdjectivesTable(70, new String[] {"ornate","dragon","ugly","serpent","dwarf-crafted","elf-crafted"});
        AdjectivesTable timeAdjective = new AdjectivesTable(60, new String[] {"old","weathered","new","ancient"});

        this.adjectiveTypes = Arrays.asList(timeAdjective, featureAdjective);
        this.exclusives = null;
    }
    public Map<WearSlot, Armor> armorMapGenerator(int probability){
        Map<WearSlot, Armor> mainArmor = new HashMap<>();
        if (Math.random()*100 < probability) {
            mainArmor.put(WearSlot.torso, getEntity(ArmorTypes.jacket));
            if (Math.random() * 100 < 50) {
                mainArmor.put(WearSlot.legs, getEntity(ArmorTypes.pants));
            }
            if (Math.random() * 100 < 15) {
                mainArmor.put(WearSlot.head, getEntity(ArmorTypes.helmet));
            }
        }
        return mainArmor;
    }

    @Override
    public Armor getEntity() {
        ArmorTypes type = ArmorTypes.values()[Roller.pickNumberFrom(ArmorTypes.values().length)];
        Armor armor = this.getBaseByType(type);
        armor = this.finalizeEntity(armor);
        return armor;
    }

    public Armor getEntity(ArmorTypes type) {
        Armor armor = this.getBaseByType(type);
        armor = this.finalizeEntity(armor);
        return armor;
    }

    @Override
    public Armor adjust(Armor entity, String adj) {
        entity.name = adj + " " + entity.name;
        entity.description = adj + " " + entity.description;

        switch(adj) {
            case "ornate":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 0.4)).intValue();
                break;
            case "dragon":
                entity.weight = new Double(Math.floor(entity.weight * 1.2)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 1.9)).intValue();
                break;
            case "ugly":
                entity.weight = new Double(Math.floor(entity.weight * 1)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 1.1)).intValue();
                break;
            case "serpent":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 1.3)).intValue();
                break;
            case "dwarf-crafted":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 2.1)).intValue();
                break;
            case "elf-crafted":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 1.7)).intValue();
                break;
            case "old":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 0.7)).intValue();
                break;
            case "weathered":
                entity.weight = new Double(Math.floor(entity.weight * 0.9)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 0.8)).intValue();
                break;
            case "new":
                entity.weight = new Double(Math.floor(entity.weight * 1.1)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 1.2)).intValue();
                break;
            case "ancient":
                entity.weight = new Double(Math.floor(entity.weight * 0.8)).intValue();
                entity.damageReduction = new Double(Math.floor(entity.damageReduction * 0.1)).intValue();
                break;
        }
        return entity;
    }



    private Armor getBaseByType(ArmorTypes type) {
        String name = type + " " + String.valueOf(Math.floor(Math.random()*1000));
        switch(type) {
            case helmet:
                return new Armor(name, type.toString(), 5,4, Arrays.asList(WearSlot.head));
            case shield:
                return new Armor(name, type.toString(), 9,6, Arrays.asList(WearSlot.offHand));
            case gloves:
                return new Armor(name, type.toString(), 1, 2, Arrays.asList(WearSlot.hands));
            case shoes:
                return new Armor(name, type.toString(), 1, 2, Arrays.asList(WearSlot.feet));
            case pants:
                return new Armor(name, type.toString(), 2, 3, Arrays.asList(WearSlot.legs));
            case jacket:
                return new Armor(name, type.toString(), 3, 4, Arrays.asList(WearSlot.torso));

        }
        return null;
    }


}
