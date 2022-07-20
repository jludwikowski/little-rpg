package org.littleRpg.model;

public class Human extends Monster{

    public int gamerId;
    public int location;

    public void adjust(Human adjust){
        super.adjust(adjust);
        this.location = adjust.location;
    }

}
