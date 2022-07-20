package org.littleRpg.engine;

public class Roller {

    public static int pickNumberFrom(int MaxValue) {
        return new Double(Math.floor(Math.random()*MaxValue)).intValue();
    }
}
