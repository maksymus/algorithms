package org.geeks;

import java.util.Arrays;

public class Candy {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Candy().candies(new int[]{1, 3, 2, 5, 4})));
    }

    public int[] candies(int[] ratings) {
        int[] candies = new int[ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            candies[i] = 1;
        }

        for (int i = 0; i < ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                if (candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }
        }

        for (int i = ratings.length - 1; i <= 0; i--) {
            if (i <  ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                if (candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                }
            }
        }

        return candies;
    }
}
