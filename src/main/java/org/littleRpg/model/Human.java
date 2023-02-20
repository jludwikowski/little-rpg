package org.littleRpg.model;


import org.littleRpg.generator.MonsterGenerator;
import org.littleRpg.generator.TextColorGenerator;

import java.util.*;

public class Human extends AdventurerClass{

    public int gamerId;
    public int[] location;
    public PlayerClasses adventureClass;
    Map<String, SurvivalAttribute> survivalAttributes = new HashMap<String, SurvivalAttribute>();

    public Human(String name, String description,int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill>skills) {
        super(MonsterTypes.human, name, description, maxHp, currentHp, attack, strength, mainWeapon, armor, loot, skills);

        survivalAttributes.put("thirst", new SurvivalAttribute("thirst"));
        survivalAttributes.put("hunger", new SurvivalAttribute("hunger",2,100));
    }

    public void chooseRace() {
        List <MonsterTypes> playerRaces = Arrays.asList(MonsterTypes.elf, MonsterTypes.human, MonsterTypes.orc, MonsterTypes.goblin);
        playerRaces.forEach(race -> System.out.println(race));
        MonsterGenerator monsterGenerator = new MonsterGenerator();
        while(true){
            try {
                type = playerRaces.get(readChoice("Choose your playerRace: "));
                System.out.println(type);
                Monster playerBaseType = monsterGenerator.getBaseByType(type);
                this.adjust(playerBaseType);
                this.mainWeapon = null;
                this.armor = null;
                this.loot = new EntityList();
                return;
            }catch (Exception e){
                System.out.println("i don't recognize this, try again");
            }
        }
    }

    public void chooseClass() {
        List <PlayerClasses> playerClasses = Arrays.asList(PlayerClasses.mage, PlayerClasses.paladin, PlayerClasses.warrior, PlayerClasses.priest);
        playerClasses.forEach(playerClass -> System.out.println(playerClass));
        while(true){
            try {
                this.adventureClass = playerClasses.get(readChoice("Choose your playerClass: "));
                System.out.println(adventureClass);
                Human playerBaseClass = this.getBaseByClass(adventureClass);
                this.adjust(playerBaseClass);
                return;
            }catch (Exception e){
                System.out.println("i don't recognize this, try again");
            }
        }
    }

    @Override
    public String getStats() {
        String description = super.getStats();
        for (String attributeName: survivalAttributes.keySet()){
            description += survivalAttributes.get(attributeName).getDescription() + "\n";
        }
        description += "race " + type;
        return description;
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }



    public void pickUpItems(List <Item> itemsOntheGround) {
        if(itemsOntheGround != null) {
            List<GameEntity> helpList = new ArrayList<>(itemsOntheGround);
            loot.list.addAll(helpList);
            ListIterator<GameEntity> groundItemIterator = loot.list.listIterator();
            while (groundItemIterator.hasNext()) {
                GameEntity nextOnTheGround = groundItemIterator.next();
                System.out.println("podniosłeś: " + nextOnTheGround.description);
            }
            this.loot.showList("You have in inventory: ");
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

  /*  public static void showItems(List<Item> items ) {
        if(items != null) {
            ListIterator<Item> itemIterator = items.listIterator();
            while (itemIterator.hasNext()) {
                Item nextItem = itemIterator.next();
                System.out.println("In loot you have: " + nextItem.description);

            }
        }
    }*/


    public void wear() {
        loot.showList("You have in loot: ");
        System.out.println("w tym momencie nosisz: " + mainWeapon + armor);
        int itemIndex = readChoice("Wybierz item który chcesz zalozyc");
        Item wearItem = (Item) loot.get(itemIndex);
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

    public static int readChoice(String prompt){
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public void useItem() {
        loot.showList("In loot you have: ");
        int itemIndex = readChoice("Wybierz item który chcesz uzyc");
        Item chosenItem = (Item) loot.get(itemIndex);
        if (chosenItem.type == ItemTypes.bottleOfWater){
            System.out.println("you drink: " + loot.get(itemIndex).description );
            loot.remove(itemIndex);
            survivalAttributes.get("thirst").change(-30);
            loot.add(new Item("Empty Bottle", ItemTypes.emptyBottle, ItemTypes.emptyBottle.toString(), 0.3));
        }
        if (chosenItem.type == ItemTypes.cookedMeat){
            System.out.println("you eat: " + loot.get(itemIndex).description );
            loot.remove(itemIndex);
            survivalAttributes.get("hunger").change(-10);
            System.out.println("Meat was good");
        }
        if (chosenItem.type == ItemTypes.scrollOfFire) {
            System.out.println("You used: " + loot.get(itemIndex).description);
            loot.remove(itemIndex);
            cookItem();
        }
    }

    public void cookItem() {
        System.out.println("You can put in fire your item. Choose item");
        loot.showList("In loot you have: ");
        int itemIndex2 = readChoice("Wybierz item który chcesz uzyc");
        Item chosenItem = (Item) loot.get(itemIndex2);
        if (chosenItem.type == ItemTypes.meat) {
            System.out.println("You cooked " + loot.get(itemIndex2).description);
            loot.remove(itemIndex2);
            loot.add(new Item("cookedMeat", ItemTypes.cookedMeat, ItemTypes.cookedMeat.toString(), 1.3));
        }
        if (chosenItem.type != ItemTypes.meat) {
            System.out.println("You burned " + loot.get(itemIndex2).description);
            loot.remove(itemIndex2);
        }
    }




}
