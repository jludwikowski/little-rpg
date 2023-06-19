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
        itemEffect.add(new Effect("ancientPower",5,EffectType.buff, Attribute.strength,999999));
        itemEffect.add(new Effect("elfBlessing", 5,EffectType.buff, Attribute.monsterDamageReduction,999999));
        itemEffect.add(new Effect("demonicRage",5,EffectType.buff, Attribute.attack, 9999999));
        itemEffect.add(new Effect("stoneGolem", 5, EffectType.buff, Attribute.maxHp, 9999999));

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

    public Effect getRandomEffect() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemEffect.size());
        return itemEffect.get(randomIndex);
    }

}
