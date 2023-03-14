package org.littleRpg.model;

import org.littleRpg.engine.Judge;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AdventurerClass extends Monster{

    public PlayerClasses className;
    public EntityList skills = new EntityList();
    public EntityList activeSkills = new EntityList();



    public AdventurerClass(MonsterTypes type, String name, String description, int maxHp, int currentHp, int attack, int strength, int damageReduction, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill> skills) {
        super(type, name, description, maxHp, currentHp, attack, strength, damageReduction, mainWeapon, armor, loot, skills);
    }


    public Human getBaseByClass(PlayerClasses playerClasses) {
        switch (playerClasses) {
            case mage:
                return new Human("mage", "mage", 5, 5, 30, 3, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case paladin:
                return new Human("paladin", "paladin", 20, 20, 15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case warrior:
                return new Human("warrior", "warrior", 20, 20, 15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case priest:
                return new Human("priest", "priest", 20, 20, 15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
        }
        return null;
    }

    public void learnSkill() {
        System.out.println("You can learn new skill. Choose item");
        this.loot.showList("In loot you have: ");
        int itemIndex3 = Human.readChoice("Choose item to use: ");
        Item chosenItem = (Item)loot.get(itemIndex3);
        System.out.println(chosenItem.type);
        System.out.println(className);
        if (chosenItem.type == ItemTypes.scrollOfThunderbolt && className == PlayerClasses.mage) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("Thunderbolt", 1, 5,5,0,0, false, true, false, true, false));
            System.out.println("You learned Thunderbolt");
        }
        if (chosenItem.type == ItemTypes.scrollOfBlessing && className == PlayerClasses.priest) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("BlessingArmor", 1, 0,0,8,0, false, false, true, false, true));
            System.out.println("You learned BlessingArmor");
        }
        if (chosenItem.type == ItemTypes.scrollOfStoneWarrior && className == PlayerClasses.warrior) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("StoneWarrior", 1, 5,5,0,0,false, true, false, true, false));
            System.out.println("You learned StoneWarrior");
        }
        if (chosenItem.type == ItemTypes.scrollOfLight && className == PlayerClasses.paladin) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("LightArmor", 1, 0,0,8,1, false, false, true, false, true));
            System.out.println("You learned LightArmor");
        }
        if (chosenItem.type == ItemTypes.scrollOfFireball && className == PlayerClasses.mage) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("Fireball", 1, 6,6,0,2,true, false, false, true, false));
            System.out.println("You learned Fireball");
        }
        if (chosenItem.type == ItemTypes.scrollOfStoneDefend && className == PlayerClasses.warrior) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("StoneArmor", 1, 0,0,8,2,false, false, true, false,true));
            skills.showList("skill");
            System.out.println("You learned StoneArmor");
        }
        if (chosenItem.type == ItemTypes.scrollOfBlessingDeath && className == PlayerClasses.priest) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("scrollOfBlessingDeath", 1, 5,5,0,0,false, true, false, true, false));
            System.out.println("You learned BlessingDeath");
        }
        if (chosenItem.type == ItemTypes.scrollOfLightPunch && className == PlayerClasses.paladin) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("LightPunch", 1, 5,5,0,2,false, true, false, true, false));
            System.out.println("You learned LightPunch");
        }
System.out.println(this.skills.list.size());
        /*else {
            System.out.println("you should learn special attack for your class");
        }*/
    }



    public void useSkill(Monster attacker, Place location){
        if (!skills.isEmpty()) {
            skills.showList("Your learned skills: ");
            int skillIndex = Human.readChoice("Choose skill to use: ");
            Skill chosenskill = (Skill) skills.get(skillIndex);
            if (chosenskill.attackSkill) {
                if (chosenskill.isAttackAll) {
                    Judge.attackAll(attacker, location, chosenskill);
                } else if (chosenskill.isAttackRange) {
                    Judge.rangeAttack(attacker, location, chosenskill);
                }
            }else if(chosenskill.deffendSkill){
                this.activeSkills.add(chosenskill);
            }
        }
        else {
            System.out.println("You need learned something");
        }
    }



}
