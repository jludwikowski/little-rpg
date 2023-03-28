package org.littleRpg.model;

import org.littleRpg.engine.Judge;
import org.littleRpg.engine.ListHelper;
import org.littleRpg.engine.SkillManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AdventurerClass extends Monster{

    public PlayerClasses className;
    public List<Skill> skills = new ArrayList<>();
    public List<Skill> activeSkills = new ArrayList<>();
    private SkillManager skillManager = new SkillManager();



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
        ListHelper.showList("In loot you have: ",this.loot);
        int itemIndex3 = Human.readChoice("Choose item to use: ");
        Item chosenItem = loot.get(itemIndex3);
        System.out.println(chosenItem.type);
        System.out.println(className);
        if(chosenItem.type == ItemTypes.scroll){
            Skill skill = skillManager.findSkillByName(chosenItem.effect);
            if (className == skill.adventurerClass) {
                System.out.println("You learned " + skill.name);
                loot.remove(itemIndex3);
                this.skills.add(skill);
            }else {
                System.out.println("not your class scroll!");
            }
        }

    }


    public int getAttribute(Attribute attribute){
        int base = getBaseAttribute(attribute);
        ListIterator<Skill> activeSkill = activeSkills.listIterator();
        while (activeSkill.hasNext()) {
            Skill skill = activeSkill.next();
            if(attribute == skill.buffAttribute) {
                base += skill.power;
            }
        }
        return base;
    }


    public void useSkill(Monster attacker, Place location){
        if (!skills.isEmpty()) {
            ListHelper.showList("Your learned skills: ",this.skills);
            int skillIndex = Human.readChoice("Choose skill to use: ");
            Skill chosenSkill = skills.get(skillIndex);
            if (chosenSkill.attackSkill) {
                if (chosenSkill.isAttackAll) {
                    Judge.attackAll(attacker, location, chosenSkill);
                } else if (chosenSkill.isAttackRange) {
                    Judge.rangeAttack(attacker, location, chosenSkill);
                }
            }else if(chosenSkill.isBuff){
                this.activeSkills.add(chosenSkill);
            }
        }
        else {
            System.out.println("You need learned something");
        }
    }



}
