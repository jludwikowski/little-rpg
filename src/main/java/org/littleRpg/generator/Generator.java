package org.littleRpg.generator;

import org.littleRpg.model.AdjectivesTable;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator<T> {

    public List<AdjectivesTable> adjectiveTypes;
    public List<AdjectivesTable> exclusives;

    abstract public T getEntity();

    public List<T> getEntities(int probability){
        List<T> entities = new ArrayList<T>();
        while(Math.random()*100 < probability){
            entities.add(this.getEntity());

        }
        return entities;
    }

}
