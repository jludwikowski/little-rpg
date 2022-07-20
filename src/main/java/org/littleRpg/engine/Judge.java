package org.littleRpg.engine;

import org.littleRpg.model.Monster;

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

    public static List<Monster> combat(Monster attacker, List<Monster> defenders){
        Judge.attack(attacker, defenders.get(0));
        if(defenders.get(0).currentHp <=0) {
            defenders.remove(0);
        }
        ListIterator<Monster> i = defenders.listIterator();
        while(i.hasNext()) {
            Monster nextMonster = i.next();
            Judge.attack(nextMonster, attacker);
        }
        return defenders;
    }

}
