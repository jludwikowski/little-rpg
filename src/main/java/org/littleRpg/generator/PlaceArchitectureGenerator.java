package org.littleRpg.generator;

import org.littleRpg.model.AdjectivesTable;

import java.util.Arrays;

public class ArchitectureGenerator {
    public ArchitectureGenerator() {
        AdjectivesTable sizeAdjectives = new AdjectivesTable(60, new String[] {"big","small","huge","enormous"});
        AdjectivesTable featureAdjective = new AdjectivesTable(80, new String[] {"ugly","strange","dark"});

        this.adjectiveTypes = Arrays.asList(sizeAdjectives, featureAdjective);
        this.exclusives = null;
    }


}
