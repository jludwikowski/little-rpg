package org.littleRpg.model;

import org.littleRpg.generator.TextColorGenerator;

import java.util.*;

public class Human extends Monster{

    public int gamerId;
    public int[] location;
    public List<SurvivalAttribute> survivalAttributes = new ArrayList<>();

    //public Item items;
    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot, int actualThirst) {
        super(name, description,maxHp, currentHp, attack, strength, mainWeapon, armor, loot);
        survivalAttributes.add(new SurvivalAttribute("thirst"));
        survivalAttributes.add(new SurvivalAttribute("hunger",2,100));
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }

    public SurvivalAttribute getSurvivalAttributeByName(String name){
        ListIterator<SurvivalAttribute> survivalAttributesIterator = survivalAttributes.listIterator();
        while(survivalAttributesIterator.hasNext()){
            SurvivalAttribute att = survivalAttributesIterator.next();
            if(att.name.equalsIgnoreCase(name)){
                return att;
            }
        }
        return null;
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
        Iterator<SurvivalAttribute> iterator = this.survivalAttributes.listIterator();
        while(iterator.hasNext()){
            SurvivalAttribute survivalAttribute = iterator.next();
            survivalAttribute.change(survivalAttribute.defaultStep);
        }
    }

    public boolean isExausted() {
        Iterator<SurvivalAttribute> iterator = this.survivalAttributes.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().isMax()){
                return true;
            }
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
            System.out.println("wypijasz: " + loot.get(itemIndex).description );
            loot.remove(itemIndex);
            SurvivalAttribute thirst = getSurvivalAttributeByName("thirst");
            thirst.change(-30);
            loot.add(new Item("Empty Bottle", ItemTypes.emptyBottle, ItemTypes.emptyBottle.toString(), 0.3));
        }
    }


}
