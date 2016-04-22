package org.interviewelements.tree;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Balanced binary tree realization.
 * 
 * 1. Every node is either red or black. 2. The root is always black. 3. If a
 * node is red, its children must be black (although the converse isn't
 * necessarily true). 4. Every path from the root to a leaf, or to a null child,
 * must contain the same number of black nodes.
 * 
 * @author maksym
 * @param <T>
 */
public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T> {

    class RedBlackNode extends BinaryNode {
        boolean red = true;

        RedBlackNode(T data) {
            super(data);
        }

        RedBlackNode(T data, boolean red) {
            super(data);
            this.red = red;
        }
    }

    private RedBlackNode root;

    public void insert(T key) {
        if (find(key) != null) {
            return;
        }

        if (root == null) {
            root = new RedBlackNode(key, false);
        }

        throw new NotImplementedException();
    }

    public void delete(T key) {
        throw new NotImplementedException();
    }

    /**
     * The number of black nodes on a path from root to leaf.
     * 
     * @param node
     * @return
     */
    int getBlackHeight(Node<T> node) {

        if (root == null) {
            return 0;
        }

        int count = 0;
        BinaryNode current = root;

        while (current.data.compareTo(node.data) != 0) {
            RedBlackNode rbNode = (RedBlackNode) current;

            if (!rbNode.red) {
                count++;
            }

            if (current.data.compareTo(node.data) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }

            if (current == null) {
                return 0;
            }
        }

        return count;
    }
}
