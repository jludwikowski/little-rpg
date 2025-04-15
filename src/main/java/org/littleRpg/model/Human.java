package org.littleRpg.model;


import org.littleRpg.engine.ListHelper;
import org.littleRpg.generator.MonsterGenerator;

import java.io.Serializable;

import java.util.*;

public class Human extends AdventurerClass implements Serializable{

    public int gamerId;
    public int[] location;
    public int attributePoints;
    Map<String, SurvivalAttribute> survivalAttributes = new HashMap<String, SurvivalAttribute>();




    public Human(String name, String description,int maxHp, int currentHp, int maxMana,  int currentMana, int attack,
                 int strength, int damageReduction, Weapon mainWeapon, Map<WearSlot, Armor> mainArmor, List<Item> loot,
                 List<Skill>skills, int goldCoins, SpecialType specialType, int exp, int monsterLevel, Archetype archetype,
                 int attributePoints, Map<MonsterTypes, Integer> monsterKillCounter) {
        super(MonsterTypes.human, name, description, maxHp, currentHp, maxMana, currentMana, attack, strength,
                damageReduction,  mainWeapon, mainArmor, loot, skills, goldCoins, specialType, exp, monsterLevel,
                archetype);
        this.attributePoints = attributePoints;
        this.monsterKillCounter = monsterKillCounter;
        survivalAttributes.put("thirst", new SurvivalAttribute("thirst"));
        survivalAttributes.put("hunger", new SurvivalAttribute("hunger",2,100));
        monsterKillCounter.put(MonsterTypes.ghul, 0);
        monsterKillCounter.put(MonsterTypes.human, 0);
        monsterKillCounter.put(MonsterTypes.demon, 0);
        monsterKillCounter.put(MonsterTypes.elf, 0);
        monsterKillCounter.put(MonsterTypes.giant, 0);
        monsterKillCounter.put(MonsterTypes.goblin, 0);
        monsterKillCounter.put(MonsterTypes.ogr, 0);
        monsterKillCounter.put(MonsterTypes.orc, 0);
        monsterKillCounter.put(MonsterTypes.slime, 0);
        monsterKillCounter.put(MonsterTypes.vampire, 0);
        monsterKillCounter.put(MonsterTypes.werewolf, 0);


    }

    public void chooseRace() {
        List <MonsterTypes> playerRaces = List.of(MonsterTypes.elf, MonsterTypes.human, MonsterTypes.orc, MonsterTypes.goblin);
        playerRaces.forEach(race -> System.out.println(race));
        MonsterGenerator monsterGenerator = new MonsterGenerator();
        while(true){
            try {
                type = playerRaces.get(readChoice("Choose your playerRace: "));
                System.out.println(type);
                Monster playerBaseType = monsterGenerator.getBaseByType(type);
                this.adjust(playerBaseType);
                this.loot = new ArrayList<Item>();
                return;
            }catch (Exception e){
                System.out.println("i don't recognize this, try again");
            }
        }
    }

    public void chooseClass() {
        List <PlayerClasses> playerClasses = List.of(PlayerClasses.mage, PlayerClasses.paladin, PlayerClasses.warrior, PlayerClasses.priest);
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
        String description = """
                maxHp: %.0f
                currentHP: %.0f
                
                """.formatted(this.maxHp, this.currentHp);
        /*String description = "maxHp: " + String.valueOf(this.maxHp) + "\n" + "currentHP: " + String.valueOf(this.currentHp) + "\n" +
                "actualHp: " + String.valueOf(getPercentStats(this.currentHp, this.maxHp)+"%") + "\n" + getBar(Attribute.maxHp) + "\n" +
                "actualMana: " + String.valueOf(getPercentStats(this.currentMana, this.maxMana)+"%") + "\n" + "attack: " + String.valueOf(getAttribute(Attribute.attack)) + "\n" +
                "strength: " + String.valueOf(getAttribute(Attribute.strength)) + "\n" +
                "damageReduction: " + String.valueOf(getAttribute(Attribute.monsterDamageReduction)+ "\n"+
                "goldCoins: " + this.goldCoins + "\n"+
                "exp: " + (int) Math.floor(this.exp) + "\n");*/
        for (String attributeName: survivalAttributes.keySet()){
            description += survivalAttributes.get(attributeName).getDescription() + "\n";
        }
        description += "race " + type;
        return description;
    }
//podczas testów refactor usunął private
    
    int getPercentStats(int a, int b){
        if(b > 0) {
            int result = a * 100 / b;
            return result;
        }
        return 0;
    }
    private int getPercentStats (float a, float b){
        if(b > 0) {
            int result = (int) Math.floor(a * 100 / b);
            return result;
        }
        return 0;
    }

    private String getBar(Attribute attribute){
        String fullAttribute = "*";
        String emptyAttribute = "-";
        int currentAttributeValue = 0;
        if(attribute == Attribute.maxHp){
            currentAttributeValue = (int) Math.floor(this.currentHp);
        }
        int percentAttribute = getPercentStats(currentAttributeValue, getAttribute(attribute));
        String fullChars = fullAttribute.repeat(percentAttribute);
        String emptyChars = emptyAttribute.repeat(100 - percentAttribute);

        return "[" + fullChars + emptyChars + ']';
    }
    @Override
    public void adjust(Monster adjust){
        //super.adjust(adjust);
        //this.location = adjust.location;
        this.currentHp += adjust.currentHp;
        this.maxHp += adjust.maxHp;
        if(currentHp > maxHp){
            this.currentHp = maxHp;
        }
        this.attack += adjust.attack;
        this.strength += adjust.strength;
        this.monsterDamageReduction += adjust.monsterDamageReduction;
        for (Map.Entry<WearSlot,Item> entry : equippedItems.entrySet()){
            Item equippedItem = adjust.equippedItems.get(entry.getKey()) != null ?
                    adjust.equippedItems.get(entry.getKey()) : entry.getValue();
            equippedItems.replace(entry.getKey(), equippedItem);
        }
    }

    public void pickUpItems(List <Item> itemsOntheGround) {
        if(itemsOntheGround == null || itemsOntheGround.isEmpty()){
            System.out.println("there is nothing here");
        }
        if(itemsOntheGround != null) {
            loot.addAll(itemsOntheGround);
            itemsOntheGround.stream().forEach(itemOnTheGround -> System.out.println("podniosłeś: " + itemOnTheGround.description));
            itemsOntheGround.clear();
            ListHelper.showList("You have in inventory: ",this.loot,false);
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

    public static int readChoice(String prompt) throws RuntimeException{
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int getLevel() {
        return (int)(Math.floor(exp/300)+1);
    }
    public void checkLevelPoints(){
        if(getLevel()>this.monsterLevel){
            int difference = getLevel() - this.monsterLevel;
            this.attributePoints += (difference * 2);
            this.monsterLevel = getLevel();
        }
    }

    public void attributePointsExchange(){
        List<String> attributeList = List.of("maxHp", "maxMana", "attack", "armor", "strength");

        Scanner scanner = new Scanner(System.in);
        System.out.println("On your account is "+ this.attributePoints + " archetypePoints\n" +
                "Do you want exchange archetypePoints? Y/N");
        String choose = scanner.nextLine();
        if (choose.equalsIgnoreCase("N")){
            return;
        }
        if(choose.equalsIgnoreCase("Y")){
            ListHelper.showList("You have in inventory: ",attributeList,false);
            int attribute = readChoice("Choose number of attribute");
            String attributePoints = attributeList.get(attribute-1);
            try{
                switch(attributePoints){
                    case "maxHp":
                        this.maxHp += 10;
                        break;
                    case "maxMana":
                        this.maxMana += 10;
                        break;
                    case "attack":
                        this.attack += 6;
                        break;
                    case "armor":
                        this.monsterDamageReduction += 2;
                        break;
                    case "strength":
                        this.strength += 2;
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }
        }
    }


    public void useItem() {
        ListHelper.showList("You have in inventory: ",this.loot,true);
        int itemIndex = readChoice("Choose item to use");
        if(itemIndex == 0){
            return;
        }
        Item chosenItem = loot.get(itemIndex-1);
        if (chosenItem.type == ItemTypes.bottleOfWater){
            System.out.println("you drink: " + chosenItem.description );
            loot.remove(itemIndex);
            survivalAttributes.get("thirst").change(-30);
            loot.add(new Item("Empty Bottle", ItemTypes.emptyBottle, ItemTypes.emptyBottle.toString(), 0.3,2));
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
        ListHelper.showList("You have in inventory: ",this.loot,true);
        int itemIndex2 = readChoice("Wybierz item który chcesz uzyc");
        if(itemIndex2 == 0){
            return;
        }
        Item chosenItem = loot.get(itemIndex2-1);
        switch (chosenItem.type){
            case meat:
                System.out.println("You cooked " + chosenItem.description);
                loot.remove(itemIndex2);
                loot.add(new Item("cookedMeat", ItemTypes.cookedMeat, ItemTypes.cookedMeat.toString(), 1.3,5));
                break;
            default:
                System.out.println("You burned " + chosenItem.description);
                loot.remove(itemIndex2);
                break;

        }

    }




}
