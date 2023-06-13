package org.littleRpg.engine;

import org.littleRpg.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EffectMenager implements Serializable {

    private List<Effect> itemEffect = new ArrayList<>();

    public EffectMenager() {
        itemEffect.add(new Effect("ancientPower",1 5,5,true,false, EffectType.attackBuff));
        itemEffect.add(new Effect("elfBlessing",1, 5, 5,false, true, EffectType.defendBuff));
        itemEffect.add(new Effect("demonicRage",1,5,5,true,false,EffectType.attackBuff ));
        itemEffect.add(new Effect("stoneGolem", 1,5,5, false, true, EffectType.defendBuff));

    }
}
