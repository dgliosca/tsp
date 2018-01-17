package com.project.GA;
import java.util.*;

public class GeneticAlgUtils {

    public static Random random = new Random();

    public static double randomDouble(double max) {
        return random.nextDouble() * max;
    }

    /**
        This method generate a double random number in the range between min and max
     */
    public static double randomDouble(double min, double max) {
        return randomDouble(max - min) + min;
    }

    /**
        This method generate an int random number in the range between min and max
     */
    public static int getRandomInt(int min, int max) {
        return (int) Math.floor(random.nextDouble() * (max - min + 1)) + min;
    }

    /**
        This method generate a sequence of number in the range between min and max
     */
    public static int[] getRndSeqInt(int min, int max) {
        int[] array = new int[max - min + 1];

        ArrayList<Integer> list = new ArrayList<Integer>(max);
        for(int i = min; i <= max; i++) {
            list.add(i);
        }
        int i = 0;
        while(list.size() > 0) {
            int index = random.nextInt(list.size());
            array[i] = list.remove(index);
            i++;
        }

        return array;
    }


}

