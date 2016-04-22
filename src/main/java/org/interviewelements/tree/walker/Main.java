package org.interviewelements.tree.walker;

import static org.interviewelements.tree.walker.Tree.Node;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("Running test");

        Tree<Integer> tree = new Tree<Integer>();
        Node<Integer> root = tree.add(null, 1);

        Node<Integer> two = tree.add(root, 2);
        Node<Integer> three = tree.add(root, 3);
        Node<Integer> four = tree.add(root, 4);

        Node<Integer> five = tree.add(three, 5);
        Node<Integer> six = tree.add(three, 6);

        Node<Integer> seven = tree.add(four, 7);

        Node<Integer> eight = tree.add(five, 8);
        Node<Integer> nine = tree.add(five, 9);

        Node<Integer> ten = tree.add(six, 10);

        TreeWalker[] treeWalkers = {new DirectTreeWalker(), new SymetricTreeWalker(), new ReverseTreeWalker()};
        for (TreeWalker walker : treeWalkers) {
            walker.walk(tree);
        }
    }
}
