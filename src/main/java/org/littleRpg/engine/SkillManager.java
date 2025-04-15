package org.littleRpg.engine;

import org.littleRpg.model.*;
import java.io.Serializable;
import java.util.*;

public class SkillManager implements Serializable {
    private List<Skill> skillList = new ArrayList<>();



    public SkillManager(){
        //można też utworzyć skillBuildera osobno jak w monsterGenerator i dodać do listy
        skillList.add(new Skill.SkillBuilder("Thunderbolt", "A thunderbolt strikes with blinding light and deafening sound, unleashing raw, electric fury that devastates all in its path.")
                .setAdventurerClasses(List.of(PlayerClasses.mage))
                .setEffect(null)
                .setAttack(5)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(true)
                .setType(SkillType.attack)
                .build());
        skillList.add(new Skill.SkillBuilder("Fireball", "A blazing fireball erupts from the caster's hand, exploding upon impact in a searing inferno that engulfs everything in its vicinity.")
                .setAdventurerClasses(List.of(PlayerClasses.mage))
                .setEffect(null)
                .setAttack(5)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(true)
                .setType(SkillType.attack)
                .build());
        skillList.add(new Skill.SkillBuilder("LightPunch", "A quick, precise punch delivered with just enough force to stagger an opponent without slowing the attacker down.")
                .setAdventurerClasses(List.of(PlayerClasses.paladin))
                .setEffect(null)
                .setAttack(5)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(true)
                .setType(SkillType.attack)
                .build());
        skillList.add(new Skill.SkillBuilder("BlessingDeath", "A dark enchantment that simultaneously blesses the caster with immense power while condemning their target to an inevitable demise.")
                .setAdventurerClasses(List.of(PlayerClasses.priest))
                .setEffect(null)
                .setAttack(5)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(true)
                .setType(SkillType.attack)
                .build());
        skillList.add(new Skill.SkillBuilder("StoneWarrior", "The StoneWarrior buff encases the target in a protective layer of stone, greatly reducing the impact of incoming strikes.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("Armor buff","protective layer of stone")
                    .setType(EffectType.buff)
                    .setPower(3)
                    .setBuffAttribute(Attribute.monsterDamageReduction)
                    .setActivationLength(10)
                    .build())
                .setAttack(0)
                .setPower(3)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("Light", "The Llight buff envelops the target in a gentle, radiant glow, diminishing the damage taken from all attacks.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("Light", "radiant glow, diminishing the damage taken from all attacks.")
                    .setType(EffectType.buff)
                    .setPower(3)
                    .setBuffAttribute(Attribute.monsterDamageReduction)
                    .setActivationLength(10)
                    .build())
                .setAttack(1)
                .setPower(0)
                .setManaCost(3)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("StoneDefend", "The StoneDefend buff surrounds the target with a sturdy barrier of rock, significantly reducing damage taken from all attacks.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("StoneDefend", "sturdy barrier of rock")
                        .setType(EffectType.buff)
                        .setPower(3)
                        .setBuffAttribute(Attribute.monsterDamageReduction)
                        .setActivationLength(10)
                        .build())
                .setAttack(0)
                .setPower(3)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("Blessing", "The Blessing buff surrounds the target with a divine aura, reducing damage taken from all attacks and enhancing resilience.")
                .setAdventurerClasses(List.of(PlayerClasses.priest))
                .setEffect(new Effect.EffectBuilder("Blessing", "reducing damage taken from all attacks and enhancing resilience")
                        .setType(EffectType.buff)
                        .setPower(3)
                        .setBuffAttribute(Attribute.strength)
                        .setActivationLength(10)
                        .build())
                .setAttack(0)
                .setPower(0)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("Enrage", "The Enrage buff fuels the target with intense fury, significantly increasing their attack power and aggression for a short duration.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("Enrage", "significantly increasing attack power and aggression for a short duration")
                        .setType(EffectType.buff)
                        .setPower(3)
                        .setBuffAttribute(Attribute.attack)
                        .setActivationLength(10)
                        .build())
                .setAttack(0)
                .setPower(3)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("Rage", "The Rage buff ignites the target's fury, drastically boosting their attack power and speed, making every strike more devastating.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("Rage", "rastically boosting attack power and speed")
                        .setType(EffectType.buff)
                        .setPower(3)
                        .setBuffAttribute(Attribute.attack)
                        .setActivationLength(10)
                        .build())
                .setAttack(0)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        skillList.add(new Skill.SkillBuilder("BattleStance", "The BattleStance buff enhances the target's combat readiness, increasing their maximum HP and bolstering their overall endurance in battle.")
                .setAdventurerClasses(List.of(PlayerClasses.warrior))
                .setEffect(new Effect.EffectBuilder("BattleStance", "increasing maximum HP and bolstering overall endurance in battle")
                        .setType(EffectType.buff)
                        .setPower(3)
                        .setBuffAttribute(Attribute.maxHp)
                        .setActivationLength(10)
                        .build())
                .setAttack(0)
                .setPower(5)
                .setManaCost(5)
                .setRanged(false)
                .setArea(false)
                .setType(SkillType.buff)
                .build());
        /*skillList.add(new Skill("Heal", null,new Effect("Heal",3,EffectType.buff,Attribute.heal,10),0,10,5, false,false,
                SkillType.heal));*/

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


