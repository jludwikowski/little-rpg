package org.littleRpg.model;

import org.littleRpg.engine.ListHelper;

import java.util.*;

public class LivingEntity extends GameEntity {
    public Map<WearSlot, Item> equippedItems = new HashMap<>();
    public List<Item> loot = new ArrayList<>();
    //List<Item> replacedItems = new ArrayList<>();
    //List<Item> equippedItemsToDrop = new ArrayList<>();


    public LivingEntity(String name, String description, List<Item> loot, Weapon mainWeapon, Map<WearSlot, Armor> mainArmor) {
        super(name, description);
        this.loot = loot;
        if (mainWeapon != null) {
            addToWearItems(mainWeapon.wearSlots, mainWeapon);
        }
        if (mainArmor != null) {
            for (Map.Entry<WearSlot, Armor> entry : mainArmor.entrySet()) {
                {
                    addToWearItems(entry.getValue().wearSlots, entry.getValue());
                }

            }
        }
    }

    public List<Item> dropItems() {
        for (Map.Entry<WearSlot,Item> entry : equippedItems.entrySet()){
            if(entry.getValue() != null) {
                System.out.println(entry.getValue().description);
            }
        }

        List<Item> dropedItems = new ArrayList<>();
        if (this.loot != null) {
            dropedItems.addAll(this.loot);
            this.loot.clear();
        }
        dropedItems.addAll(equippedItems.values());
        equippedItems.clear();
        return dropedItems;
    }

    public void showEquipItems() {
        if (equippedItems.values().isEmpty()) {
            System.out.println("No equipped items");
        }
        if (!equippedItems.values().isEmpty()) {
            equippedItems.values().forEach(item -> {
                System.out.println(name + " is wearing: " + item.description);
            });
        }


    }

    public void wear() {
        ListHelper.showList("You have in inventory: ", this.loot,true);
        showEquipItems();
        int itemIndex = Human.readChoice("Choose item to wear");
        if(itemIndex == 0){
            return;
        }
        Item wearItem = this.loot.get(itemIndex-1);
        if (wearItem == null) {
            System.out.println("Item is null");
            return;
        }
        List<Item> replacedItems = addToWearItems(wearItem.wearSlots , wearItem);
        for (Item replacedItem: replacedItems) {
            addToInventory(replacedItem);
        }
        ListHelper.showList("You have in inventory: ", this.loot,false);
    }



    public List<Item> addToWearItems(List<WearSlot> slots, Item item){
        List<Item> replacedItems = new ArrayList<>();
        if (item != null) {
            for(WearSlot slot : slots) {
                if (equippedItems.get(slot) != null) {
                    if(!replacedItems.contains(equippedItems.get(slot))) {
                        Item replacedItem = (equippedItems.replace(slot, item));
                        replacedItems.add(replacedItem);
                        for(WearSlot replacedSlot : replacedItem.wearSlots){
                            if(equippedItems.get(replacedSlot) == replacedItem){
                                equippedItems.remove(replacedSlot);
                            }
                        }
                    }
                } else {
                    equippedItems.put(slot, item);
                }
            }
            this.loot.remove(item);
        }
        return replacedItems;
    }


    public void addToInventory (Item item){
        if(item != null){
            this.loot.add(item);
        }
    }
    public void takeOff(){
        List<Item> equippedItemsToDrop = new ArrayList<>(equippedItems.values());
        ListHelper.showList("You have equipped: ", equippedItemsToDrop,true);
        int itemIndex = Human.readChoice("Choose item to drop");
        if(itemIndex == 0){
            return;
        }
        Item dropItem = equippedItemsToDrop.get(itemIndex-1);
        for(WearSlot dropItemSlot : dropItem.wearSlots) {
            equippedItems.remove(dropItemSlot);
        }
        addToInventory(dropItem);
        equippedItemsToDrop.clear();
        showEquipItems();
    }

    public int getArmorValue() {
        int armorValue = 0;
        Iterator<Item> equippedItemsIterator = equippedItems.values().iterator();
        while (equippedItemsIterator.hasNext()) {
            Item equippedItem = equippedItemsIterator.next();
            if (equippedItem instanceof Armor) {
                armorValue += ((Armor) equippedItem).damageReduction;
            }
        }
        return armorValue;
    }

    public String getArmorDescription() {
        String armorDescription = "";
        for (Map.Entry<WearSlot,Item> entry : equippedItems.entrySet()){
            if(entry.getValue() instanceof Armor) {
                armorDescription = armorDescription.concat("\n wearing " + entry.getValue().description); //tworzenie stringa
            }
        }
        return armorDescription;
    }
}

