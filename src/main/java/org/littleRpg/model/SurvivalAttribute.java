package org.littleRpg.model;

public class SurvivalAttribute {
    String name;
    int defaultStep;
    int value;
    int max;

    public SurvivalAttribute(String name){
        this.name = name;
        this.defaultStep = 5;
        this.max = 100;
        this.value = 0;
    }

    public SurvivalAttribute(String name, int step, int max){
        this.name = name;
        this.defaultStep = step;
        this.max = max;
        this.value = 0;
    }

    void change(int value) {
        this.value += value;
        if(this.value>max){
            this.value = this.max;
        }
        if(this.value<0){
            this.value = 0;
        }
    }

    public boolean isMax(){
        if(this.value == this.max){
            return true;
        }
        return false;
    }

}
