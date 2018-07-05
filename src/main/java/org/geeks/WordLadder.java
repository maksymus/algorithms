package org.geeks;

import java.util.*;

/**
 * Input:  Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}
 *   start = TOON
 *   target = PLEA
 * Output: 7
 * Explanation: TOON - POON - POIN - POIE - PLIE - PLEE - PLEA
 */
public class WordLadder {
    private Map<String,Integer> steps = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println(new WordLadder().walk("TOON", "PLEA", 
                Arrays.asList("POON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN")));
    }
    
    public int walk(String start, String end, List<String> words) {
        List<String> dict = new ArrayList<>(words);
        
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        steps.put(start, 1);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            Iterator<String> dictIter = dict.iterator();
            
            while (dictIter.hasNext()) {
                String word = dictIter.next();
                if (isOneStepChange(current, word)) {
                    dictIter.remove();
                    steps.put(word, steps.get(current) + 1);
                    
                    queue.add(word);

                    if (word.equals(end)) 
                        return steps.get(word);
                }
            }
        }
        
        return -1;
    }
    
    private boolean isOneStepChange(String from, String to) {
        int diff = Math.abs(from.length() - to.length());
        
        for (int i = 0; i < Math.min(from.length(), to.length()); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
        }
        
        return diff <= 1;
    }
}
