package org.interviewelements.tree.walker;

import algorithm.tree.walker.Tree.Node;

import java.util.ServiceLoader;

public class Notation {

    public static void main(String[] args) {
        System.out.println("Running test");

        Tree<String> tree = new Tree<String>();
        Node<String> mult = tree.add(null, "*");

        Node<String> plus1 = tree.add(mult, "+");
        Node<String> plus2 = tree.add(mult, "+");

        tree.add(plus1, "a");
        tree.add(plus1, "b");

        tree.add(plus2, "a");
        tree.add(plus2, "c");

        ServiceLoader<TreeWalker> walkers = ServiceLoader.load(TreeWalker.class);

        for (TreeWalker walker : walkers) {
            walker.walk(tree);
        }
    }
}
