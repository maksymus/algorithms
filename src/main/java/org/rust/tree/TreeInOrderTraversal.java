package org.rust.tree;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Implement a class that implements an InOrder Iterator on a Binary Tree
 */
public class TreeInOrderTraversal {
    public static void main(String[] args) {
        Node tree = new Node(125, new Node(100), new Node(200));


        Iterator<Node> iterator = new InOrderIteratorImpl(tree);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().data);
        }

        recursive(tree, node -> System.out.println(node.data));
        iterative(tree, node -> System.out.println(node.data));
    }

    // recursive
    public static void recursive(Node tree, Consumer<Node> consumer) {
        if (tree == null)
            return;

        recursive(tree.left, consumer);
        consumer.accept(tree);
        recursive(tree.right, consumer);
    }

    // iterative
    public static void iterative(Node root, Consumer<Node> consumer) {
        Deque<Node> stack = new LinkedList<>();

        for (Node tmp = root; tmp != null; tmp = tmp.left)
            stack.push(tmp);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            consumer.accept(node);

            Node right = node.right;
            for (Node tmp = right; tmp != null; tmp = tmp.left)
                stack.push(tmp);
        }
    }

    // iterator
    private static class InOrderIteratorImpl implements Iterator<Node> {
        private Deque<Node> result = new LinkedList<>();

        private InOrderIteratorImpl(Node root) {
            for (Node tmp = root; tmp != null; tmp = tmp.left)
                result.push(tmp);
        }

        @Override
        public boolean hasNext() {
            return !result.isEmpty();
        }

        @Override
        public Node next() {
            Node node = result.pop();

            Node right = node.right;
            for (Node tmp = right; tmp != null; tmp = tmp.left)
                result.push(tmp);

            return node;
        }
    }
}
