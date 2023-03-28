package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class SkillManager {
    private List<Skill> skillList = new ArrayList<>();


    public SkillManager(){
        skillList.add(new Skill("Thunderbolt", PlayerClasses.mage,1, 5,5,null,0,
                false, true, false, true));
        skillList.add(new Skill("Fireball", PlayerClasses.mage,1, 5,5,null,0,
                false, true, false, true));
        skillList.add(new Skill("LightPunch", PlayerClasses.paladin,1, 5,5,null,0,
                false, true, false, true));
        skillList.add(new Skill("BlessingDeath", PlayerClasses.priest,1, 5,5,0,null,
                false, true, false, true));
        skillList.add(new Skill("StoneWarrior", PlayerClasses.warrior,1, 0,3,Attribute.monsterDamageReduction,3,
                false, false, true, false));
        skillList.add(new Skill("Light", PlayerClasses.warrior,1, 0,3,Attribute.monsterDamageReduction,3,
                false, false, true, false));
        skillList.add(new Skill("StoneDefend", PlayerClasses.warrior,1, 0,3,Attribute.monsterDamageReduction,3,
                false, false, true, false));
        skillList.add(new Skill("Blessing", PlayerClasses.priest,1, 0,0, Attribute.monsterDamageReduction,2,
                false, false, true, false));
        skillList.add(new Skill("Enrage", PlayerClasses.warrior,1, 0,3, Attribute.Strength,3,
                false, false, true, false));
        skillList.add(new Skill("Rage", PlayerClasses.warrior,1, 0,5, Attribute.attack,2,
                false, false, true, false));
        skillList.add(new Skill("BattleStance", PlayerClasses.warrior,1, 0,5, Attribute.maxHp,3,
                false, false, true, false));

    }

    public String getRandomSkillName() {
        Random random = new Random();
        int randomIndex = random.nextInt(skillList.size());
        return skillList.get(randomIndex).name;
    }

    public Skill findSkillByName(String name){
        ListIterator<Skill> skill = skillList.listIterator();
        while(skill.hasNext()){
            Skill nextSkill = skill.next();
            if(nextSkill.name == name){
                return nextSkill;
            }
        }
        return null;
    }


}


