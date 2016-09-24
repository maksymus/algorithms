package org.saurabhschool;

import java.util.Objects;

import org.util.BinTreeNode;

public class BinTree {

    public static int maxHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = maxHeight(node.getLeft());
        int rightDepth = maxHeight(node.getRight());

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * A binary tree is said to be balanced if for each node in the tree, the difference in the height of its left
     * and right subtrees is at most one.
     */
    public static boolean isBalanced(BinTreeNode<?> node) {
        return maxBalancedHeight(node) != -1;
    }

    private static int maxBalancedHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = maxBalancedHeight(node.getLeft());
        if (leftDepth == -1)
            return -1;

        int rightDepth = maxBalancedHeight(node.getRight());
        if (rightDepth == -1)
            return -1;

        if (Math.abs(leftDepth - rightDepth) > 1)
            return -1; // unbalanced

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Write a function that takes as input the root of a binary tree and returns
     * true or false depending on whether the tree is symmetric.
     */
    public static boolean isSymmetric(BinTreeNode<?> node) {
        if (node == null)
            return true;

        return isSymmetric(node.getLeft(), node.getRight());
    }

    private static boolean isSymmetric(BinTreeNode<?> node1, BinTreeNode<?> node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        return Objects.equals(node1.getKey(), node2.getKey())
                && isSymmetric(node1.getLeft(), node2.getRight())
                && isSymmetric(node1.getRight(), node2.getLeft());
    }

    /**
     * Design an efficient algorithm for computing the LCA of nodes a and b
     * in a binary tree in which nodes do not have a parent pointer.
     */
    public static BinTreeNode<?> lowestCommonAncestor(BinTreeNode<?> node, BinTreeNode<?> a, BinTreeNode<?> b) {
        if (node == null)
            return null;

        if (node == a || node == b)
            return node;

        BinTreeNode<?> left = lowestCommonAncestor(node.getLeft(), a, b);
        BinTreeNode<?> right = lowestCommonAncestor(node.getRight(), a, b);

        if (left != null && right != null)
            return node;

        return left != null ? left : right;
    }
    
    /**
     * Print all Possible Paths
     */
    public static void printPaths(BinTreeNode<?> root) {
        printPaths(root, new Object[root.getSize()], 0);
    }
    
    private static void printPaths(BinTreeNode<?> node, Object[] objs, int length) {
        if (node == null) 
            return;
        
        objs[length++] = node.getKey();
        
        if (node.getLeft() == null && node.getRight() == null) {
            printPath(objs, length);
        } else {
            printPaths(node.getLeft(), objs, length);
            printPaths(node.getRight(), objs, length);
        }
    }
    
    private static void printPath(Object[] objs, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(objs[i] + ",");
        }
        System.out.println();
    }
    

    public static void main(String[] args) {
        BinTreeNode<Integer> root = new BinTreeNode<>(30, 
                new BinTreeNode<>(20), new BinTreeNode<>(40, 
                        new BinTreeNode<>(35), null));
        printPaths(root);
//        System.out.println(maxHeight(root));
    }
}
