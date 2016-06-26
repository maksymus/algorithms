package org.util;

public class BinTreeNode<S> {
    private S key;           // sorted by key
    private BinTreeNode<S> left, right;  // left and right subtrees

    public BinTreeNode(S key, BinTreeNode<S> left, BinTreeNode<S> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BinTreeNode(S key) {
        this.key = key;
    }

    public BinTreeNode<S> getLeft() {
        return left;
    }
    
    public void setLeft(BinTreeNode<S> left) {
        this.left = left;
    }

    public BinTreeNode<S> getRight() {
        return right;
    }
    
    public void setRight(BinTreeNode<S> right) {
        this.right = right;
    }

    public S getKey() {
        return key;
    }
}
