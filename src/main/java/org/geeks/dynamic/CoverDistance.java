package org.geeks.dynamic;

/**
 * Given a distance dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
 * 
 * Examples:
 * Input:  n = 3
 * Output: 4
 * Below are the four ways
 *  1 step + 1 step + 1 step
 *  1 step + 2 step
 *  2 step + 1 step
 *  3 step
 */
public class CoverDistance {
    public int countrec(int dist) {
        if (dist < 0)
            return 0;
        
        if (dist == 0)
            return 1;
        
        return countrec(dist - 1) + countrec(dist - 2) + countrec(dist - 3);
    }
    
    public int count(int dist) {
        int[] cache = new int[dist+1];
        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;
        
        for (int i = 3; i <= dist; i++) {
            cache[i] = cache[i-1] + cache[i-2] + cache[i-3];
        }
        
        return cache[dist];
    }
    
    public static void main(String[] args) {
        System.out.println(new CoverDistance().count(3));
    }
}
