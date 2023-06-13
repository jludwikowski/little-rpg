package org.littleRpg.model;

import java.io.Serializable;

public class GameEntity implements Serializable
{
    public String name = "";
    public String description = "";

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public GameEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
