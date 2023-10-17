package org.littleRpg.model;

import org.littleRpg.engine.EffectManager;
import org.littleRpg.engine.Judge;
import org.littleRpg.engine.ListHelper;
import org.littleRpg.engine.SkillManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class AdventurerClass extends Monster implements Serializable {

    public PlayerClasses className;
    public List<Skill> skills = new ArrayList<>();
    private SkillManager skillManager = new SkillManager();
    public List<Item> specialItems = new ArrayList<>();



    public AdventurerClass(MonsterTypes type, String name, String description, int maxHp, int currentHp, int maxMana, int currentMana, int attack, int strength, int damageReduction, Weapon mainWeapon, Map<WearSlot, Armor> mainArmor, List<Item> loot, List<Skill> skills) {
        super(type, name, description, maxHp, currentHp, maxMana, currentMana, attack, strength, damageReduction, mainWeapon, mainArmor, loot, skills);
    }


    public Human getBaseByClass(PlayerClasses playerClasses) {
        switch (playerClasses) {
            case mage:
                return new Human("mage", "mage", 5, 5, 100,100, 30, 3, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case paladin:
                return new Human("paladin", "paladin", 20, 20, 50, 50,15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case warrior:
                return new Human("warrior", "warrior", 20, 20, 30, 30,15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case priest:
                return new Human("priest", "priest", 20, 20, 50, 50,15, 1, 0, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
        }
        return null;
    }

    public void learnSkill() {
        System.out.println("You can learn new skill. Choose item");
        ListHelper.showList("In loot you have: ", this.loot, true);
        int itemIndex3 = Human.readChoice("Choose item to use: ");
        Item chosenItem = loot.get(itemIndex3);
        System.out.println(chosenItem.type);
        System.out.println(className);
        if (chosenItem.type == ItemTypes.scroll && chosenItem instanceof Scroll) {
            Scroll scroll = (Scroll) chosenItem;
            Skill skill = skillManager.findSkillByName(scroll.skillName);
            if (skill.adventurerClasses == null || skill.adventurerClasses.contains(className)) {
                System.out.println("You learned " + skill.name);
                this.skills.add(skill);
                loot.remove(itemIndex3);
            } else {
                System.out.println("not your class scroll!");
            }
        }
    }


    public int getAttribute(Attribute attribute) {
        int base = getBaseAttribute(attribute);
        ListIterator<Item> specialItem = specialItems.listIterator();
        while (specialItem.hasNext()){
            Effect effect = EffectManager.getItemEffect(specialItem.next().name);
            if (attribute == effect.buffAttribute) {
                base += effect.power;
            }
        }
        ListIterator<Skill> activeSkill = skills.listIterator();
        while (activeSkill.hasNext()) {
            Skill skill = activeSkill.next();
            if (attribute == skill.buffAttribute) {
                base += skill.power;
            }
        }
        return base;
        }




    public void useSkill(Monster attacker, Place location) {
        if (!skills.isEmpty()) {
            ListHelper.showList("Your learned skills: ", this.skills, true);
            int skillIndex = Human.readChoice("Choose skill to use: ");
            if(skillIndex == 0){
                return;
            }
            Skill chosenSkill = skills.get(skillIndex-1);
            if(chosenSkill.manaCost >= currentMana){
                System.out.println("Out of mana");
                return;
            }
            currentMana = currentMana - chosenSkill.manaCost;
            if (chosenSkill.type == SkillType.attack) {
                if (chosenSkill.isArea) {
                    Judge.attackAll(attacker, location, chosenSkill);
                } else if (chosenSkill.isRanged) {
                    Judge.rangeAttack(attacker, location, chosenSkill);
                }
            } else if (chosenSkill.type == SkillType.heal) {
                if(chosenSkill.activationLength == 0) {
                    heal(chosenSkill.power);
                }else {
                    skills.add(chosenSkill);
                }
            }
            if (chosenSkill.effect != null) {
                ListIterator<Effect> effectIterator = activeEffects.listIterator();
                while (effectIterator.hasNext()) {
                    Effect nextEffect = effectIterator.next();
                    if (nextEffect.buffAttribute == chosenSkill.effect.buffAttribute) {
                        effectIterator.remove();
                    }
                }
                activeEffects.add(chosenSkill.effect);
            }
        }


    }
}
