package org.littleRpg.model;

import org.littleRpg.engine.ListHelper;

import java.util.*;

public class LivingEntity extends GameEntity {
    public Map<WearSlot, Item> equippedItems = new HashMap<>();
    public List<Item> loot = new ArrayList<>();
    //List<Item> equippedItemsToDrop = new ArrayList<>();


    public LivingEntity(String name, String description, List<Item> loot, Weapon mainWeapon, Armor armor) {
        super(name, description);
        this.loot = loot;
        equippedItems.put(mainWeapon.slot, mainWeapon);
        equippedItems.put(armor.slot, armor);
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
        WearSlot wearSlot = wearItem.slot;
        System.out.println("type" + wearSlot);
        this.loot.remove(itemIndex);
        if (equippedItems.get(wearSlot) != null) {
            Item dropedItem = equippedItems.replace(wearSlot, wearItem);
            equippedItems.replace(wearSlot, wearItem);
            System.out.println("you take of " + dropedItem.description);
            this.loot.add(dropedItem);
        } else {
            equippedItems.put(wearItem.slot, wearItem);
            System.out.println("Was empty slot");
        }
        ListHelper.showList("You have in inventory: ", this.loot,false);
    }

    public void takeOff(){
        List<Item> equippedItemsToDrop = new ArrayList<>(equippedItems.values());
        ListHelper.showList("You have equipped: ", equippedItemsToDrop,true);
        int itemIndex = Human.readChoice("Choose item to drop");
        if(itemIndex == 0){
            return;
        }
        Item dropItem = equippedItemsToDrop.get(itemIndex-1);
        WearSlot dropSlot = dropItem.slot;
        equippedItems.replace(dropSlot, dropItem, null);
        this.loot.add(dropItem);
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
        /*Iterator<Item> equippedItemsIterator = equippedItems.values().iterator();
        while (equippedItemsIterator.hasNext()) {
            Item equippedItem = equippedItemsIterator.next();
            if (equippedItem instanceof Armor) {
                armorDescription = "\n wearing " + equippedItem.description;
            }
        }*/
        return armorDescription;
    }
}