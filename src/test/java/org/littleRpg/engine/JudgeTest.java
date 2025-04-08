package org.littleRpg.engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.generator.MonsterGenerator;
import org.littleRpg.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.littleRpg.model.Biome.hill;

public class JudgeTest {
    Human player = new Human("","player",20,20,20,20, 105,
            0,0, null,null, new ArrayList<Item>(),
            null,100, null,0,0,
            null, 0, new HashMap<>());
    //Actions underTest = new Actions(player);

    Place [][][] testWorld = new MapPlace[2][20][20];
    public MonsterGenerator monsterGenerator =  new MonsterGenerator();
    Monster monster1 = new Monster(MonsterTypes.goblin,"monster1","",10,10,0,0,105,5,1,
            new Weapon("stick","",3, 2,2, false,false, Arrays.asList(WearSlot.mainHand),10,1),
            null,new ArrayList<>(),null,0,null,0,1,null);
    Monster monster2 = new Monster(MonsterTypes.ghul,"monster2","",10,10,0,0,5,5,1,
            new Weapon("stick","",3, 2,2, false,false, Arrays.asList(WearSlot.mainHand),10,1),
            null,new ArrayList<>(),null,0,null,0,1,null);

    MapPlace westPlace = new MapPlace(1, hill, "westHill", "westHill", null,null, null, null);
    List<Monster> monsters = new ArrayList<>();
    @BeforeEach
    public void setup() {
        monsters.add(monster1);
        monsters.add(monster2);

        player.location = new int[]{0, 5, 6};
        testWorld[0][5][6] = westPlace;
        westPlace.monsters = monsters;
    }
    @Test
    public void shouldAttackWithNullSkillAndDamage(){
        Judge.attack(monster1, Arrays.asList(monster2), null, westPlace, false);
        assertTrue(monster2.currentHp < monster2.maxHp);
    }
    @Test
    public void missedAttack(){
        monster1.attack = -2;

        Judge.attack(monster1, Arrays.asList(monster2), null, westPlace, false);
        assertTrue(monster2.currentHp == monster2.maxHp);
    }
    @Test
    public void monsterAttackTest(){
        Judge.monsterAttack(player,westPlace);
        assertTrue(player.currentHp<player.maxHp);
    }
    @Test
    public void combatTest(){
        Judge.combat(player, westPlace, 0,null, player);
        assertTrue(player.currentHp< player.maxHp);
    }
}
