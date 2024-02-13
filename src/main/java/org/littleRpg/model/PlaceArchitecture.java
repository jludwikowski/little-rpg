package org.littleRpg.model;


import org.littleRpg.generator.PlaceArchitectureGenerator;

import java.util.List;

public class PlaceArchitecture extends GameEntity {
    public PlaceArchitectureTypes type;
    public PlaceArchitecture (PlaceArchitectureTypes type, String name, String description){
     super(name, description);
     this.type = type;
     this.name = name;
     this.description = description;

    }

}
