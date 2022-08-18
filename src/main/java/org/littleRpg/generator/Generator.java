package org.littleRpg.generator;

import org.littleRpg.engine.Roller;
import org.littleRpg.model.AdjectivesTable;

import java.util.ArrayList;
import java.util.Iterator;
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

    public T finalizeEntity(T entity) {
        if(adjectiveTypes!=null && !adjectiveTypes.isEmpty()) {
            Iterator<AdjectivesTable> tableList = adjectiveTypes.listIterator();
            while(tableList.hasNext()) {
                AdjectivesTable adjectivesList = tableList.next();
                String adjective = null;
                if(Math.random()*100 < adjectivesList.entryProbability) {
                    adjective = adjectivesList.adjectives.get(Roller.pickNumberFrom(adjectivesList.adjectives.size()));
                }
                if(adjective != null) {
                    entity = this.adjust(entity, adjective);
                }
            }
        }
        return entity;
    }

    abstract public T adjust(T entity, String adj);

}
