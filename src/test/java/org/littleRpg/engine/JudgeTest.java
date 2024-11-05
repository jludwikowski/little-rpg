package org.littleRpg.engine;

import org.junit.jupiter.api.Test;
import org.littleRpg.model.Monster;
import org.littleRpg.model.MonsterTypes;
import org.littleRpg.model.Weapon;
import org.littleRpg.model.WearSlot;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JudgeTest {
    @Test
    public void shouldAttackWithNullSkillAndDamage(){
        Monster monster1 = new Monster(MonsterTypes.goblin,"monster1","",10,10,0,0,105,5,1,
                new Weapon("stick","",3, 2,2, false,false, Arrays.asList(WearSlot.mainHand),10,1),
                null,new ArrayList<>(),null,0,null,0,1,null);
        Monster monster2 = new Monster(MonsterTypes.ghul,"monster2","",10,10,0,0,5,5,1,
                new Weapon("stick","",3, 2,2, false,false, Arrays.asList(WearSlot.mainHand),10,1),
                null,new ArrayList<>(),null,0,null,0,1,null);

        Judge.attack(monster1, monster2, null);
        assertTrue(monster2.currentHp < monster2.maxHp);
    }
}
