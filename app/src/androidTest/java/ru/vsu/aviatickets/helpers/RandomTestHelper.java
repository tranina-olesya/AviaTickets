package ru.vsu.aviatickets.helpers;

import java.util.Random;

public class RandomTestHelper {
    private static Random random = new Random();

    public static int getRandomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
