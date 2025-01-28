package org.littleRpg.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.model.Place;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaceGeneratorTest {
    PlaceGenerator placeGenerator;
    @BeforeEach
    public void setup(){
        placeGenerator = new PlaceGenerator();
    }
    @Test
    public void getEntityForStartLocation(){
        Place place = placeGenerator.getEntity();
        assertTrue(place.biome != null);
    }


}
