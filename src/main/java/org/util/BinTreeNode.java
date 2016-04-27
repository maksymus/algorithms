package org.util;

public class BinTreeNode<S> {
    private S key;           // sorted by key
    private BinTreeNode left, right;  // left and right subtrees

    public BinTreeNode(S key, BinTreeNode left, BinTreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BinTreeNode(S key) {
        this.key = key;
    }

    public BinTreeNode getLeft() {
        return left;
    }

    public BinTreeNode getRight() {
        return right;
    }
}
