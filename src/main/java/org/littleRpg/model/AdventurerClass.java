package org.littleRpg.model;

import org.littleRpg.engine.Judge;

import java.util.ArrayList;
import java.util.List;

public class AdventurerClass extends Monster{
    public PlayerClasses className;
    public EntityList skills;



    public AdventurerClass(MonsterTypes type, String name, String description, int maxHp, int currentHp, int attack, int strength, Weapon mainWeapon, Armor armor, List<Item> loot, List<Skill> skills) {
        super(type, name, description, maxHp, currentHp, attack, strength, mainWeapon, armor, loot, skills);
    }


    public Human getBaseByClass(PlayerClasses playerClasses) {
        switch (playerClasses) {
            case mage:
                return new Human("mage", "mage", 5, 5, 30, 3, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case paladin:
                return new Human("paladin", "paladin", 20, 20, 15, 1, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case warrior:
                return new Human("warrior", "warrior", 20, 20, 15, 1, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
            case priest:
                return new Human("priest", "priest", 20, 20, 15, 1, null, null, new ArrayList<Item>(), new ArrayList<Skill>());
        }
        return null;
    }

    public void learnSkill() {
        System.out.println("You can learn new skill. Choose item");
        this.loot.showList("In loot you have: ");
        int itemIndex3 = Human.readChoice("Choose item to use: ");
        Item chosenItem = (Item)loot.get(itemIndex3);
        if (chosenItem.type == ItemTypes.scrollOfThunderbolt && className == PlayerClasses.mage) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("Thunderbolt", 1, 5,5,false, true));
        }
        if (chosenItem.type == ItemTypes.scrollOfLightPunch && className == PlayerClasses.paladin) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("LightPunch", 1, 5,5,false, true));
        }
        if (chosenItem.type == ItemTypes.scrollOfStoneWarrior && className == PlayerClasses.warrior) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("StoneWarrior", 1, 5,5,false, true));
        }
        if (chosenItem.type == ItemTypes.scrollOfFireball && className == PlayerClasses.mage) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("Fireball", 1, 6,6,true, false));
        }
        if (chosenItem.type == ItemTypes.scrollOfBlessing && className == PlayerClasses.priest) {
            System.out.println("You learned " + loot.get(itemIndex3).description);
            loot.remove(itemIndex3);
            this.skills.add(new Skill("Frenzy of blessing", 1, 5,5,false, true));
        }
    }



    public void useSkill(Monster attacker, Place location){
        if (!skills.isEmpty()) {
            this.skills.showList("Your learned skills: ");
            int skillIndex = Human.readChoice("Choose skill to use: ");
            Skill chosenskill = (Skill) skills.get(skillIndex);
            Judge.skillAttackAll(attacker, location, chosenskill);
        }
    }

}
