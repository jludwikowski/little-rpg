package org.littleRpg.model;


import org.littleRpg.generator.TextColorGenerator;

import java.util.*;

public class Human extends Monster{

    public int gamerId;
    public int[] location;
   // public List<SurvivalAttribute> survivalAttributes = new ArrayList<>();
    Map<String, SurvivalAttribute> survivalAttributes = new HashMap<String, SurvivalAttribute>();

    //public Item items;
    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot, int actualThirst) {
        super(name, description,maxHp, currentHp, attack, strength, mainWeapon, armor, loot);
       // survivalAttributes.add(new SurvivalAttribute("thirst"));
       // survivalAttributes.add(new SurvivalAttribute("hunger",2,100));

        survivalAttributes.put("thirst", new SurvivalAttribute("thirst"));
        survivalAttributes.put("hunger", new SurvivalAttribute("hunger",2,100));

    }



    @Override
    public String getStats() {
        String description = super.getStats();
        for (String attributeName: survivalAttributes.keySet()){
            description += survivalAttributes.get(attributeName).getDescription();
        }
        return description;
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }



    public void pickUpItems(List <Item> itemsOntheGround) {
        if(itemsOntheGround != null) {
            this.loot.addAll(itemsOntheGround);
            ListIterator<Item> groundItemIterator = itemsOntheGround.listIterator();
            while (groundItemIterator.hasNext()) {
                Item nextOnTheGround = groundItemIterator.next();
                System.out.println("podniosłeś: " + nextOnTheGround.description);
            }
            showItems(this.loot);
        }
    }

    public void timePasses(){
        survivalAttributes.forEach((key,value)->value.change(value.defaultStep));
    }

    public boolean isExausted() {
        if (survivalAttributes.entrySet().stream().filter(entry->entry.getValue().isMax()).count() > 0){
            return true;
        }
        return false;
    }

    public static void showItems(List<Item> items ) {
        if(items != null) {
            ListIterator<Item> itemIterator = items.listIterator();
            while (itemIterator.hasNext()) {
                Item nextItem = itemIterator.next();
                System.out.println("In loot you have: " + nextItem.description);

            }
        }
    }

    public void wear() {
        showItems(loot);
        System.out.println("w tym momencie nosisz: " + mainWeapon + armor);
        int itemIndex = itemChoice("Wybierz item który chcesz zalozyc");
        Item wearItem = loot.get(itemIndex);
        if(wearItem instanceof Armor) {
            Item dropArmor = armor;
            System.out.println("ściągasz: " + dropArmor.description );
            armor = (Armor) wearItem;
            loot.remove(itemIndex);
            if(dropArmor != null) {
                loot.add(dropArmor);
            }
            System.out.println("Założyłeś: " + this.armor.description);

        }
        if(wearItem instanceof Weapon) {
            Item dropMainWeapon = mainWeapon;
            if(mainWeapon != null) {
                System.out.println("ściągasz: " + dropMainWeapon.description);
            }
            mainWeapon = (Weapon) wearItem;
            loot.remove(itemIndex);
            if(dropMainWeapon != null) {
                loot.add(dropMainWeapon);
            }
            System.out.println("Założyłeś: " + this.mainWeapon.description);
        }
        if(!(wearItem instanceof Armor) && !(wearItem instanceof Weapon)) {
            System.out.println("Nie mozesz tego uzyc!");
        }

    }

    public static int itemChoice(String prompt){
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void useItem() {
        showItems(loot);
        int itemIndex = itemChoice("Wybierz item który chcesz uzyc");
        if (loot.get(itemIndex).type == ItemTypes.bottleOfWater){
            System.out.println("you drink: " + loot.get(itemIndex).description );
            loot.remove(itemIndex);
            survivalAttributes.get("thirst").change(-30);
            loot.add(new Item("Empty Bottle", ItemTypes.emptyBottle, ItemTypes.emptyBottle.toString(), 0.3));
        }
        if (loot.get(itemIndex).type == ItemTypes.meat){
            System.out.println("you eat: " + loot.get(itemIndex).description );
            loot.remove(itemIndex);
            survivalAttributes.get("hunger").change(-10);
            System.out.println("Meat was good, but cooked is better");
        }
    }


}
