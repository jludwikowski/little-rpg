package org.littleRpg.generator;

public class TextColorGenerator {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void blackText (String newText){
        System.out.print(ANSI_BLACK + newText + ANSI_RESET);
    }

    public static void redText (String newText){
        System.out.print(ANSI_RED + newText + ANSI_RESET);
    }
    public static void greenText (String newText){
        System.out.print(ANSI_GREEN + newText + ANSI_RESET);
    }

    public static void yellowText (String newText){
        System.out.print(ANSI_YELLOW + newText + ANSI_RESET);
    }
    public static void blueText (String newText){
        System.out.print(ANSI_BLUE + newText + ANSI_RESET);
    }
    public static void purpleText (String newText){
        System.out.print(ANSI_PURPLE + newText + ANSI_RESET);
    }
    public static void cyanText (String newText){
        System.out.print(ANSI_CYAN + newText + ANSI_RESET);
    }
    public static void whiteText (String newText){
        System.out.print(ANSI_WHITE + newText + ANSI_RESET);
    }

}
