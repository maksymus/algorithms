package org.interviewelements.tree.walker;

import java.util.Arrays;

public class Notation {

    public static void main(String[] args) {
        System.out.println("Running test");

        Tree<String> tree = new Tree<String>();
        Tree.Node<String> mult = tree.add(null, "*");

        Tree.Node<String> plus1 = tree.add(mult, "+");
        Tree.Node<String> plus2 = tree.add(mult, "+");

        tree.add(plus1, "a");
        tree.add(plus1, "b");

        tree.add(plus2, "a");
        tree.add(plus2, "c");

        TreeWalker[] walkers = {new DirectTreeWalker(), new SymetricTreeWalker(), new ReverseTreeWalker()};
        Arrays.asList(walkers).stream().forEach(walker -> walker.walk(tree));
    }
}
