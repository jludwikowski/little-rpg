package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Judge {

    public static void attack(Monster attacker, List<Monster> targets, Skill skill, Place location, boolean surefire) {
        targets.stream().forEach(target -> {
            if(surefire || attacker.getAttribute(Attribute.attack) > Roller.pickNumberFrom(100)){
                int damageValue = (int) Math.floor(Roller.pickNumberFrom(10));
                if(skill != null){
                    damageValue += skill.power;
                }else {
                    damageValue += attacker.getDamage();
                }
                System.out.println(attacker.getName() + " hitted " + target.getName() + " for " + damageValue + "HP!");
                target.damage(damageValue);
                attacker.exp += (0.2F * target.exp);
                checkIfDead(location, target, attacker);
            } else {
            System.out.println(attacker.getName() + " Missed!");
                attacker.exp += (0.1F * target.exp);
        }
        });
    }

    public static List<Monster> monsterAttack(Monster attacker, Place location){
        location.monsters.stream().forEach(monster -> System.out.println("You see around: " + monster.description));
        location.monsters.stream().filter(monster -> monster.aggressive).forEach(aggresiveMonster -> Judge
                .attack(aggresiveMonster, Arrays.asList(attacker),null, location, false));
        return location.monsters;
    }

    public static List<Monster> combat(Monster attacker, Place location, int monsterIndex, Skill skill, Human player){
        List<Monster> targets;
        boolean surefire = false;
        if(skill.isArea){
            targets = location.monsters;
            surefire = true;
        } else {
            if (skill.isRanged) {
                targets = Arrays.asList(chooseMonster(location));
            } else {
                targets = Arrays.asList(location.monsters.get(monsterIndex));
            }
        }
        attack(attacker, targets, skill, location, surefire);
        return location.monsters.stream().filter(target -> target.currentHp > 0).collect(Collectors.toList());
    }

    private static boolean checkIfDead(Place location, Monster nextMonster, Monster attacker) {
        if (nextMonster.currentHp <= 0) {
            if (nextMonster.loot != null && !nextMonster.loot.isEmpty()) {
                location.items.addAll(nextMonster.dropItems());
                System.out.println("You gained " + (int) Math.floor(nextMonster.exp) + " exp points");
            }
            attacker.countDeadMonsters(nextMonster);
            return true;
        }
        return false;
    }

    public static Monster chooseMonster(Place location){
        location.monsters.stream().forEach(monster -> System.out.println("You see around: " + monster.description));
        System.out.println("Choose monster for attack");
        Scanner target = new Scanner(System.in);
        int monsterIndex = target.nextInt();
        return location.monsters.get(monsterIndex);
    }


}
