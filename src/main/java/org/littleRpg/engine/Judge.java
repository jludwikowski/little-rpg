package org.littleRpg.engine;

import org.littleRpg.model.Monster;
import org.littleRpg.model.Place;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

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


    public static List<Monster> monsterAttack(Monster attacker, Place location){
        ListIterator<Monster> monsterListIterator = location.monsters.listIterator();
        while (monsterListIterator.hasNext()) {
            Monster nextMonster = monsterListIterator.next();
            System.out.println("w zasiegu wzroku masz: " + nextMonster.description);
        }
        System.out.println("gdy sie poruszyles potwory cie zauwazyly i zaczely atackowac");

        ListIterator<Monster> j = location.monsters.listIterator();
        while(j.hasNext()) {
            Monster nextMonster = j.next();
            Judge.attack(nextMonster, attacker);
        }

        return location.monsters;
    }

    public static List<Monster> combat(Monster attacker, Place location, int monsterIndex){
        Monster firstMonster = location.monsters.get(monsterIndex);
        Judge.attack(attacker, firstMonster);
        if(firstMonster.currentHp <=0) {
            if(firstMonster.loot != null && !firstMonster.loot.isEmpty()) {
                location.items.addAll(firstMonster.dropItems());
            }
            location.monsters.remove(0);
        }

        return monsterAttack(attacker, location);
    }

    public static List<Monster> specialCombat(Monster attacker, Place location){
        ListIterator<Monster> monsterListIterator = location.monsters.listIterator();
        while (monsterListIterator.hasNext()) {
            Monster nextMonster = monsterListIterator.next();
            System.out.println("w zasiegu wzroku masz: " + nextMonster.description);
        }
        System.out.println("Wybierz ktorego potwora chcesz zaatakowac");
        Scanner target = new Scanner(System.in);
        int monsterIndex = target.nextInt();
        return combat(attacker, location, monsterIndex);
    }

}
