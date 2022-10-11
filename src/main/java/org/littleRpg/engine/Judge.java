package org.littleRpg.engine;

import org.littleRpg.model.Monster;
import org.littleRpg.model.Place;

import java.util.List;
import java.util.ListIterator;

public class Judge {

    public static void attack(Monster monster1, Monster monster2) {
        double roll =  Math.random()*100;
        System.out.println("Attack is: " + monster1.getAttack() + " Roll is:" + String.valueOf(roll));
        if(monster1.getAttack() > roll) {
            int damageValue = (int) Math.floor(Math.random()*10) + monster1.getDamage();
            System.out.println(monster1.getName() + " hitted " + monster2.getName() + " for " + damageValue + "HP!");
            monster2.damage(damageValue);
        } else {
            System.out.println(monster1.getName() + " Missed!");
        }
    }

    public static List<Monster> combat(Monster attacker, Place location){
        Monster firstMonster = location.monsters.get(0);
        Judge.attack(attacker, firstMonster);
        if(firstMonster.currentHp <=0) {
            if(firstMonster.loot != null && !firstMonster.loot.isEmpty()) {
                location.items.addAll(firstMonster.dropItems());
            }
            location.monsters.remove(0);
        }
        ListIterator<Monster> i = location.monsters.listIterator();
        while(i.hasNext()) {
            Monster nextMonster = i.next();
            Judge.attack(nextMonster, attacker);
        }
        return location.monsters;
    }

}
