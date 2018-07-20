package org.rust.tree;

/**
 * Inorder successor of a node in binary Search Tree (BST) is the next node in inorder traversal.
 * Write a method to find the inorder successor of a given value "d" in a BST.
 */
public class InorderSuccessorBST {
    public static void main(String[] args) {
        Node left = new Node(100);
        Node right = new Node(200);

        Node tree = new Node(125, left, right);

        Node successor = successor(tree, left);
        System.out.println(successor != null ? successor.data : "no successor");
    }

    public static Node successor(Node node, Node given) {
        Node successor = null;

        while (node != null) {
            if (given.data < node.data) {
                successor = node;
                node = node.left;
            } else if (given.data >= given.data) {
                node = node.right;
            }
        }

        return successor;
    }
}
