package org.littleRpg.model;

public class Scroll extends Item {

    public String skillName;

    public Scroll(String name, ItemTypes type, String description, double weight, Effect effect, String skillName, int price){
        super(name,type,description,weight,effect,null, price);
        this.skillName = skillName;
    }

}
