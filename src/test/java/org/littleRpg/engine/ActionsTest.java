package org.littleRpg.engine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.littleRpg.generator.WorldGenerator;
import org.littleRpg.model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.littleRpg.model.Biome.forest;
import static org.littleRpg.model.Biome.shop;

public class ActionsTest {

    @Test
    void mapPrinterShouldPrintPlayerPositionSymbol() {
        Place[][][] world = new WorldGenerator().generateWorld();
        int[] location = {0, 5, 5};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Actions.mapPrinter(world, location);
        String output = out.toString();
        assertTrue(output.contains("00"), "Mapa powinna zawierać symbol 00 oznaczający gracza");

        System.setOut(System.out); // przywróć wyjście (sprzątanie po teście w konsoli)
    }

    @Test
    void mapPrinterShouldPrintForestSymbol() {
        Place[][][] world = new WorldGenerator().generateWorld();
        world[0][6][6].biome = forest;
        int[] location = {0, 5, 5};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Actions.mapPrinter(world, location);
        String output = out.toString();
        assertTrue(output.contains("##"), "Mapa zawiera las (##)");
        System.setOut(System.out);
    }


    @Test
    void mapPrinterShouldHaveException() {
        Place[][][] world = new WorldGenerator().generateWorld();
        world[0][6][6].biome = shop;
        int[] location = {0, 5, 5};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Actions.mapPrinter(world, location);
        String output = out.toString();
        assertTrue(output.contains("??"), "Mapa zawiera default (??)");
        System.setOut(System.out);
    }

}
