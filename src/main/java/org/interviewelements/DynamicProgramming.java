package org.interviewelements;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DynamicProgramming {
    /**
     * 15.11 Given two strings, represented as arrays of characters A and B,
     * compute the minimum number of edits needed to transform the first string into the
     */
    public static int levenshtein(String str1, String str2) {
        
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];        
        
        for (int i = 0; i <= str1.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 0; j <= str2.length(); j++)                                 
            distance[0][j] = j;                                                  
                                                                                 
        for (int i = 1; i <= str1.length(); i++)                                 
            for (int j = 1; j <= str2.length(); j++) {
                int insert =  distance[i - 1][j] + 1;
                int delete =  distance[i][j - 1] + 1;
                int replace = distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1);
                
                distance[i][j] = Stream.of(insert, delete, replace).min(Comparator.naturalOrder()).get();
            }
                                                                                 
        return distance[str1.length()][str2.length()];      
    }
    
    /**
     * Problem 15.13 Given text , i.e., a string of words separated by single blanks ,
     * decompose the text into lines such that no word is split across lines and the messiness of the
     * decomposition is minimized . Each line can hold no more than L characters. How would you
     * change your algorithm if the messiness is the sum of the messinesses of all but the last line?
     * https://www.youtube.com/watch?v=RORuwHiblPc
     */
    public static void prettyPrint(List<String> words, int maxLineLength) {
        List<Integer> wordLens = words.stream().map(word -> word.length()).collect(Collectors.toList());
        
        int[][] extraSpaces = new int[words.size()][words.size()]; 
        int[][] lineCosts = new int[words.size()][words.size()];
        int[] cost = new int[words.size() + 1];
        int[] steps = new int[words.size()];
        
        for (int i = 0; i < wordLens.size(); i++) {
            extraSpaces[i][i] = maxLineLength - wordLens.get(i);
            for (int j = i + 1; j < wordLens.size(); j++) {
                extraSpaces[i][j] = extraSpaces[i][j - 1] - wordLens.get(j) - 1;
            }
        }
        
        for (int i = 0; i < wordLens.size(); i++) {
            for (int j = i; j < wordLens.size(); j++) {
                if (extraSpaces[i][j] < 0)
                    lineCosts[i][j] = Integer.MAX_VALUE;
                else
                    lineCosts[i][j] = (int) Math.pow(extraSpaces[i][j], 2);
            }
        }

        for (int i = wordLens.size() - 1; i >= 0; i--) {
            cost[i] = Integer.MAX_VALUE;
            for (int j = wordLens.size() - 1; j >= i; j--) {
                if (lineCosts[i][j] != Integer.MAX_VALUE && (lineCosts[i][j] + cost[j + 1] < cost[i])) {
                    cost[i] = cost[j + 1] + lineCosts[i][j];
                    steps[i] = j;
                }
            }
        }
        
        for (int step = 0; step < wordLens.size(); step = steps[step] + 1) {
            for (int cnt = step; cnt <= steps[step]; cnt++) {
                System.out.print(words.get(cnt) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        prettyPrint(Arrays.asList("The quick brown fox jumps over the lazy dog".split(" ")), 10);
//        prettyPrint(Arrays.asList("aaa bb bb ccccc".split(" ")), 6);
//        prettyPrint(Arrays.asList("Maksym Rod likes to code".split(" ")), 10);
    }
}
