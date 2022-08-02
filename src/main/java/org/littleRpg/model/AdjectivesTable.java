package org.littleRpg.model;

import org.littleRpg.engine.Roller;
import java.util.ArrayList;
import java.util.List;

public class AdjectivesTable {

    public int entryProbability;
    public List<String> adjectives;

    public AdjectivesTable(int probability, String[] adjectives){
        this.entryProbability = probability;
        this.adjectives = new ArrayList<String>();
        if(adjectives!=null) {
            for (int i = 0; i < adjectives.length; i++) {
                this.adjectives.add(adjectives[i]);
            }
        }
    }

    public String getAdjective(){
        if (Math.random()*100 < this.entryProbability) {
            return adjectives.get(Roller.pickNumberFrom(adjectives.size()));
        }
        return null;
    }

}
