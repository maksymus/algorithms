package org.saurabhschool;

import org.util.BinTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinTree {

    public static int maxHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = maxHeight(node.getLeft());
        int rightDepth = maxHeight(node.getRight());

        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    public static int minHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = minHeight(node.getLeft());
        int rightDepth = minHeight(node.getRight());

        return Math.min(leftDepth, rightDepth) + 1;
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
    
    /**
     * Given an array of numbers, return true if given array can represent preorder traversal of a Binary Search Tree, 
     * else return false. Expected time complexity is O(n).
     * 
     * Input:  pre[] = {2, 4, 3}
     * Output: true
     * Given array can represent preorder traversal of below tree
     * 2
     *  \
     *   4
     *  /
     * 3
     * 
     */
    public static boolean isPreorderTraversal(int[] arr) {
        if (arr.length == 0)
            return true;
        
        Deque<Integer> stack = new LinkedList<>();
        int minVal = Integer.MIN_VALUE;
        
        stack.push(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            if (!stack.isEmpty() && stack.peek() < arr[i]) {
                minVal = stack.pop();
            }
            
            if (arr[i] < minVal)
                return false;
            
            stack.push(arr[i]);
        }
        
        return true;     
    }
    
    /**
     * Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. Given a binary tree, 
     * print the top view of it. The output nodes can be printed in any order. Expected time complexity is O(n)
     * A node x is there in output if x is the topmost node at its horizontal distance. Horizontal distance of left 
     * child of a node x is equal to horizontal distance of x minus 1, and that of right child is horizontal distance 
     * of x plus 1.
     *        
     *         1
     *      /     \
     *     2       3
     *    /  \    / \
     *   4    5  6   7
     *   Top view of the above binary tree is
     *   4 2 1 3 7 
     */
    static class Node {
        int n; Node left; Node right;

        public Node(int n, Node left, Node right) {
            this.n = n;
            this.left = left;
            this.right = right;
        }

        public Node(int n) {
            this.n = n;
        }
    }
    
    static class QNode {
        Node node;
        int dist;

        public QNode(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    
    public static void printTopView(Node root) {
        int leftBorder = Integer.MAX_VALUE, rightBorder = Integer.MIN_VALUE;
        QNode qroot = new QNode(root, 0);
        
        Queue<QNode> queue = new LinkedList<QNode>();
        queue.add(qroot);
        
        while (!queue.isEmpty()) {
            QNode qnode = queue.poll();
            
            if (qnode.dist < leftBorder) {
                System.out.println(qnode.node.n);
                leftBorder = qnode.dist; 
            } else if (qnode.dist > rightBorder) {
                System.out.println(qnode.node.n);
                rightBorder = qnode.dist; 
            }
            
            Node left = qnode.node.left;
            Node right = qnode.node.right;
            
            if (left != null) {
                queue.add(new QNode(left, qnode.dist - 1));
            }
            
            if (right != null) {
                queue.add(new QNode(right, qnode.dist + 1));
            }
        }
    } 
    

    public static void main(String[] args) {
//        BinTreeNode<Integer> root = new BinTreeNode<>(30, 
//                new BinTreeNode<>(20), new BinTreeNode<>(40, 
//                        new BinTreeNode<>(35), null));
//        printPaths(root);
//        System.out.println(maxHeight(root));
        
        System.out.println(isPreorderTraversal(new int[] { 40, 30, 35, 32, 80, 100 }));
    }
}
