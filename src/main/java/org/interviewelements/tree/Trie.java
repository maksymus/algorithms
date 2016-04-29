package org.interviewelements.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Prefix tree implementation.
 */
public class Trie implements Tree<String> {

    private class TrieNode extends Node<Character> {

        private Map<Character, TrieNode> leaves = new HashMap<Character, TrieNode>();
        private boolean terminal;

        TrieNode(Character data, boolean terminal) {
            super(data);
            this.terminal = terminal;
        }

        public void addNode(char c, boolean terminal) {
            leaves.put(c, new TrieNode(c, terminal));
        }

        public boolean isTerminal() {
            return terminal;
        }

        public void setTerminal(boolean terminal) {
            this.terminal = terminal;
        }
    }

    private TrieNode root = new TrieNode((char) 0, false);

    @Override
    public void insert(String key) {
        TrieNode current = root;

        for (char c : key.toCharArray()) {
            Map<Character, TrieNode> leaves = current.leaves;

            if (!leaves.containsKey(c)) {
                current.addNode(c, false);
            }

            current = leaves.get(c);
        }

        current.setTerminal(true);
    }

    @Override
    public void delete(String key) {
        delete(root, key, 0);
    }

    private void delete(TrieNode node, String str, int pos) {
        if (pos >= str.length())
            return;

        TrieNode leave = node.leaves.get(str.charAt(pos));

        if (leave == null)
            return;

        delete(leave, str, pos + 1);

        if (pos == str.length() - 1)
            leave.setTerminal(false);

        if (leave.leaves.size() == 0 && !leave.isTerminal()) {
            node.leaves.remove(str.charAt(pos));
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean contains(String key) {
        TrieNode current = root;

        for (char c : key.toCharArray()) {
            Map<Character, TrieNode> leaves = current.leaves;

            if (!leaves.containsKey(c)) {
                return false;
            }

            current = leaves.get(c);
        }

        return current.isTerminal();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("category");
        trie.insert("cell");

        System.out.println(trie.contains("cat"));
        System.out.println(trie.contains("cat1"));
        System.out.println(trie.contains("category"));
        System.out.println(trie.contains("category1"));

        trie.insert("cat1");
        trie.delete("cat1");

        System.out.println("=== " + trie.contains("cat"));
        System.out.println(trie.contains("cat1"));
        System.out.println(trie.contains("category"));

        trie.delete("a");
    }
}
