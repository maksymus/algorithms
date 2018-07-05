package org.util;

public class BinTreeNode<S> {
    private S key;           // sorted by key
    private BinTreeNode<S> left, right;  // left and right subtrees
    private int size;

    public BinTreeNode(S key, BinTreeNode<S> left, BinTreeNode<S> right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.size = 1 + (left != null ? left.size : 0)
                + (right != null ? right.size : 0);
    }

    public BinTreeNode(S key) {
        this.key = key;
        this.size = 1;
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
    
    public int getSize() {
        return size;
    }
}
