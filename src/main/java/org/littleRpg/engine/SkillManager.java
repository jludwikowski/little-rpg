package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.io.Serializable;
import java.util.*;

public class SkillManager implements Serializable {
    private List<Skill> skillList = new ArrayList<>();



    public SkillManager(){
        skillList.add(new Skill("Thunderbolt", Arrays.asList(PlayerClasses.mage),null, 5,5,5,
                false, true, SkillType.attack));
        skillList.add(new Skill("Fireball", Arrays.asList(PlayerClasses.mage),null, 5,5,5,
                false, true, SkillType.attack));
        skillList.add(new Skill("LightPunch", Arrays.asList(PlayerClasses.paladin),null, 5,5,5,
                false, true, SkillType.attack));
        skillList.add(new Skill("BlessingDeath", Arrays.asList(PlayerClasses.priest), null, 5,5,5,
                false, true, SkillType.attack));
        skillList.add(new Skill("StoneWarrior", Arrays.asList(PlayerClasses.warrior),new Effect("Armor buff",3,EffectType.buff,Attribute.monsterDamageReduction,10), 0,3,5,
                false, false, SkillType.buff));
        skillList.add(new Skill("Light", Arrays.asList(PlayerClasses.warrior),1, 0,3,5,Attribute.monsterDamageReduction,3,
                false, false, SkillType.buff));
        skillList.add(new Skill("StoneDefend", Arrays.asList(PlayerClasses.warrior),1, 0,3,5,Attribute.monsterDamageReduction,3,
                false, false, SkillType.buff));
        skillList.add(new Skill("Blessing", Arrays.asList(PlayerClasses.priest),1, 0,0,5, Attribute.monsterDamageReduction,2,
                false, false, SkillType.buff));
        skillList.add(new Skill("Enrage", Arrays.asList(PlayerClasses.warrior),1, 0,3,5, Attribute.strength,3,
                false, false, SkillType.buff));
        skillList.add(new Skill("Rage", Arrays.asList(PlayerClasses.warrior),1, 0,5,5, Attribute.attack,2,
                false, false, SkillType.buff));
        skillList.add(new Skill("BattleStance", Arrays.asList(PlayerClasses.warrior),1, 0,5,5, Attribute.maxHp,3,
                false, false, SkillType.buff));
        skillList.add(new Skill("Heal", null,1, 0,10,5, null,3,
                false, false, SkillType.heal));

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


