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
        itemEffect.add(new Effect.EffectBuilder("ancientPower","Ancient Power - greatly enhances the user's Strength by channeling the immense force of ancient warriors, allowing them to perform extraordinary feats of might.")
                .setPower(5)
                .setType(EffectType.buff)
                .setBuffAttribute(Attribute.strength)
                .setActivationLength(999999)
                .build());
        itemEffect.add(new Effect.EffectBuilder("elfBlessing","Elf Blessing grants the user enhanced agility and resilience, significantly reducing incoming damage and allowing them to evade attacks with ease." )
                .setPower(5)
                .setType(EffectType.buff)
                .setBuffAttribute(Attribute.monsterDamageReduction)
                .setActivationLength(999999)
                .build());
        itemEffect.add(new Effect.EffectBuilder("demonicRage","Demonic Rage unleashes a surge of dark power, dramatically boosting the user's attack strength and ferocity in battle." )
                .setPower(5)
                .setType(EffectType.buff)
                .setBuffAttribute(Attribute.attack)
                .setActivationLength(999999)
                .build());
        itemEffect.add(new Effect.EffectBuilder("stoneGolem","Stone Golem fortifies the user's body with the resilience of solid rock, significantly increasing their maximum health." )
                .setPower(5)
                .setType(EffectType.buff)
                .setBuffAttribute(Attribute.maxHp)
                .setActivationLength(999999)
                .build());

    }
 /*   public static Effect getItemEffect(String effectName) {
        ListIterator<Effect> effectListIterator = itemEffect.listIterator();
        while(effectListIterator.hasNext()){
            Effect effect = effectListIterator.next();
            if(effectName == effect.name){
                return effect;
            }
        }
        return null;
    }*/

    public static Effect getItemEffect(String effectName) {
        for (Effect effect : itemEffect) {
            if (effectName.equals(effect.name)) {
                return effect;
            }
        }
        return null;
    }

    public String getRandomEffectName() {
        /*Random random = new Random();
        int randomIndex = random.nextInt(itemEffect.size());
        return itemEffect.get(randomIndex).name;*/
        return getRandomEffect().name;
    }

    public Effect getRandomEffect() {
        Random random = new Random();
        /*int randomIndex = random.nextInt(itemEffect.size());
        return itemEffect.get(randomIndex);*/
        return itemEffect.get(random.nextInt(itemEffect.size()));
    }

}
