package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class EffectManager implements Serializable {

    private static List<Effect> itemEffect = new ArrayList<>();

    public EffectManager() {
        itemEffect.add(new Effect("ancientPower",1, 5,5,true,false, EffectType.attackBuff, Attribute.Strength));
        itemEffect.add(new Effect("elfBlessing",1, 5, 5,false, true, EffectType.defendBuff, Attribute.monsterDamageReduction));
        itemEffect.add(new Effect("demonicRage",1,5,5,true,false,EffectType.attackBuff, Attribute.attack ));
        itemEffect.add(new Effect("stoneGolem", 1,5,5, false, true, EffectType.defendBuff, Attribute.maxHp));

    }
    public static Effect getItemEffect(String effectName) {
        ListIterator<Effect> effectListIterator = itemEffect.listIterator();
        while(effectListIterator.hasNext()){
            Effect effect = effectListIterator.next();
            if(effectName == effect.name){
                return effect;
            }
        }
        return null;
    }

    public String getRandomEffectName() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemEffect.size());
        return itemEffect.get(randomIndex).name;
    }

}
