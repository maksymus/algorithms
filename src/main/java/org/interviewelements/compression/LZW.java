package org.interviewelements.compression;

import java.util.HashMap;
import java.util.Map;

/**
 * http://habrahabr.ru/blogs/algorithm/112757/
 * 
 * @author maksym
 */
public class LZW {

    private Map<String, Integer> getCompressDictionary() {
        return new HashMap<String, Integer>() {
            {
                put("A", 0);
                put("B", 1);
                put("C", 2);
                put("D", 3);
            }
        };
    }

    private Map<Integer, String> getDecompressDictionary() {
        return new HashMap<Integer, String>() {
            {
                put(0, "A");
                put(1, "B");
                put(2, "C");
                put(3, "D");
            }
        };
    }

    public static void main(String[] args) {
        String str = "ABCABCABCABBACACDACCABAABBACADADCACCABABBACACDADDACCC"
                + "CCCABBBBBABACACDADCACDADCABBCACADDDDDCCCABABBACCADAADBCAD";

        LZW m = new LZW();
        System.out.println(str.equals(m.decompress(m.compress(str))));
    }

    public String compress(String str) {

        Map<String, Integer> dict = getCompressDictionary();

        String res = "";
        String chain = "", newchain = "";

        for (char c : str.toCharArray()) {
            newchain = chain + c;

            if (dict.containsKey(newchain)) {
                chain = newchain;
            } else {
                res += (char) (int) dict.get(chain);
                dict.put(newchain, dict.size());
                chain = "" + c;
            }
        }

        res += (char) (int) dict.get(chain);

        return res;
    }

    public String decompress(String str) {
        Map<Integer, String> dict = getDecompressDictionary();

        String chain = dict.get((int) str.charAt(0));
        String res = chain;

        for (int i = 1; i < str.toCharArray().length; i++) {

            int code = (int) str.toCharArray()[i];
            String s = dict.get(code);

            if (s != null) {
                dict.put(dict.size(), chain + s.charAt(0));
                chain = s;
            } else {
                chain += chain.charAt(0);
                dict.put(dict.size(), chain);
            }

            res += chain;
        }

        return res;
    }
}
