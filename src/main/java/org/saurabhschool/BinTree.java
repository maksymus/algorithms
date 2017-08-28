package org.saurabhschool;

import org.util.BinTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinTree {

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
