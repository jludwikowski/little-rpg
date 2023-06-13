package org.littleRpg.model;


import org.littleRpg.engine.ListHelper;
import org.littleRpg.generator.MonsterGenerator;

import java.io.Serializable;

import java.util.*;

public class Human extends AdventurerClass implements Serializable{

    public int gamerId;
    public int[] location;
    Map<String, SurvivalAttribute> survivalAttributes = new HashMap<String, SurvivalAttribute>();

    public Human(String name, String description,int maxHp, int currentHp, int maxMana,  int currentMana, int attack, int strength, int damageReduction, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill>skills, Item mainNecklace, Item mainRing) {
        super(MonsterTypes.human, name, description, maxHp, currentHp, maxMana, currentMana, attack, strength, damageReduction,  mainWeapon, armor, loot, skills, mainNecklace, mainRing);

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
                this.mainNecklace = null;
                this.mainRing = null;
                this.loot = new ArrayList<Item>();
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
                this.className = playerClasses.get(readChoice("Choose your playerClass: "));
                System.out.println(className);
                Human playerBaseClass = this.getBaseByClass(className);
                this.adjust(playerBaseClass);
                return;
            }catch (Exception e){
                System.out.println("i don't recognize this, try again");
            }
        }
    }

    @Override
    public String getStats() {
        String description = "maxHp: " + String.valueOf(this.maxHp) + "\n" + "currentHP: " + String.valueOf(this.currentHp) + "\n" +
                "actualHp: " + String.valueOf(getPercentStats(this.currentHp, this.maxHp)+"%") + "\n" + getBar(Attribute.maxHp) + "\n" +
                "actualMana: " + String.valueOf(getPercentStats(this.currentMana, this.maxMana)+"%") + "\n" +
                "attack: " + String.valueOf(getAttribute(Attribute.attack)) + "\n" +
                "strength: " + String.valueOf(getAttribute(Attribute.Strength)) + "\n" +
                "damageReduction: " + String.valueOf(getAttribute(Attribute.monsterDamageReduction)+ "\n");
        for (String attributeName: survivalAttributes.keySet()){
            description += survivalAttributes.get(attributeName).getDescription() + "\n";
        }
        description += "race " + type;
        return description;
    }

    private int getPercentStats (int a, int b){
        if(b > 0) {
            int result = a * 100 / b;
            return result;
        }
        return 0;
    }

    private String getBar(Attribute attribute){
        String fullAttribute = "*";
        String emptyAttribute = "-";
        int currentAttributeValue = 0;
        if(attribute == Attribute.maxHp){
            currentAttributeValue = this.currentHp;
        }
        int percentAttribute = getPercentStats(currentAttributeValue, getAttribute(attribute));
        String fullChars = fullAttribute.repeat(percentAttribute);
        String emptyChars = emptyAttribute.repeat(100 - percentAttribute);

        return "[" + fullChars + emptyChars + ']';
    }

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }

    public void skillTurnCounter () {

        if (activeSkills != null){
            ListHelper.showList("Active Skills: ",activeSkills);
            ListIterator <Skill> activeSkill = activeSkills.listIterator();
            while(activeSkill.hasNext()){
                Skill skill = activeSkill.next();
                if(skill.type == SkillType.heal){
                    this.heal(skill.power);
                    System.out.println("You are healed for " + skill.power);
                }
                skill.activationLength -= 1;
                System.out.println("skill kończy się za: " + skill.activationLength);
                if (skill.activationLength == 0){
                    activeSkill.remove();
                }
            }
        }
    }



    public void pickUpItems(List <Item> itemsOntheGround) {
        if(itemsOntheGround != null) {
            loot.addAll(itemsOntheGround);
            ListIterator<Item> groundItemIterator = loot.listIterator();
            while (groundItemIterator.hasNext()) {
                GameEntity nextOnTheGround = groundItemIterator.next();
                System.out.println("podniosłeś: " + nextOnTheGround.description);
            }
            ListHelper.showList("You have in inventory: ",this.loot);
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



    public void wear() {
        ListHelper.showList("You have in inventory: ",this.loot);
        if (mainWeapon != null) {
            System.out.println("Your equip Weapon: " + mainWeapon.description);
        }
        if (armor != null){
            System.out.println("Your equip armor: " + armor.description);
        }
        if (mainNecklace != null){
            System.out.println("Your equip Necklace: " + mainNecklace.description);
        }
        if (mainRing != null){
            System.out.println("Your equip Ring: " + mainRing.description);
        }
        if (mainWeapon == null && armor == null && mainNecklace == null && mainRing == null) {
            System.out.println("You don't have equipped equipment");
        }
        int itemIndex = readChoice("Choose item to wear");
        Item wearItem = loot.get(itemIndex);
        if(wearItem.type == ItemTypes.necklace){
                Item dropNeckless = null;
                if (mainNecklace != null) {
                    dropNeckless = mainNecklace;
                    loot.add(dropNeckless);
                    specialItems.remove(mainNecklace);
                }
                specialItems.add(wearItem);
                mainNecklace = wearItem;
                loot.remove(itemIndex);
                System.out.println("wear: " + this.mainNecklace.description);
        }

        if(wearItem.type == ItemTypes.ring){
            Item dropRing = null;
            if (mainRing != null) {
                dropRing = mainRing;
                loot.add(dropRing);
                specialItems.remove(mainRing);
            }
            specialItems.add(wearItem);
            mainRing = wearItem;
            loot.remove(itemIndex);
            System.out.println("wear: " + this.mainRing.description);
        }
        if(wearItem instanceof Armor) {
            Item dropArmor = null;
            if (armor != null) {
                dropArmor = armor;
                System.out.println("you take of: " + dropArmor.description);
            }
            armor = (Armor) wearItem;
            loot.remove(itemIndex);
            if(dropArmor != null) {
                loot.add(dropArmor);
            }
            System.out.println("you take of: " + this.armor.description);

        }
        if(wearItem instanceof Weapon) {
            Item dropMainWeapon = mainWeapon;
            if(mainWeapon != null) {
                System.out.println("you take of: " + dropMainWeapon.description);
            }
            mainWeapon = (Weapon) wearItem;
            loot.remove(itemIndex);
            if(dropMainWeapon != null) {
                loot.add(dropMainWeapon);
            }
            System.out.println("You equiped: " + this.mainWeapon.description);
        }
        if(!(wearItem instanceof Armor) && !(wearItem instanceof Weapon)) {
            System.out.println("You cannot use this!");
        }

    }

    public static int readChoice(String prompt){
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public void useItem() {
        ListHelper.showList("You have in inventory: ",this.loot);
        int itemIndex = readChoice("Choose item to use");
        Item chosenItem = loot.get(itemIndex);
        if (chosenItem.type == ItemTypes.bottleOfWater){
            System.out.println("you drink: " + chosenItem.description );
            loot.remove(itemIndex);
            survivalAttributes.get("thirst").change(-30);
            loot.add(new Item("Empty Bottle", ItemTypes.emptyBottle, ItemTypes.emptyBottle.toString(), 0.3));
        }
        if (chosenItem.type == ItemTypes.cookedMeat){
            System.out.println("you eat: " + chosenItem.description );
            loot.remove(itemIndex);
            survivalAttributes.get("hunger").change(-10);
            System.out.println("Meat was good");
        }
        if (chosenItem.type == ItemTypes.flint) {
            System.out.println("You used: " + chosenItem.description);
            cookItem();
        }
    }

    public void cookItem() {
        System.out.println("You can put in fire your item. Choose item");
        ListHelper.showList("You have in inventory: ",this.loot);
        int itemIndex2 = readChoice("Wybierz item który chcesz uzyc");
        Item chosenItem = loot.get(itemIndex2);
        switch (chosenItem.type){
            case meat:
                System.out.println("You cooked " + chosenItem.description);
                loot.remove(itemIndex2);
                loot.add(new Item("cookedMeat", ItemTypes.cookedMeat, ItemTypes.cookedMeat.toString(), 1.3));
                break;
            default:
                System.out.println("You burned " + chosenItem.description);
                loot.remove(itemIndex2);
                break;

        }

    }




}
