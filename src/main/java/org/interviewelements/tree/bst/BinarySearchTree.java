package org.interviewelements.tree.bst;

import org.interviewelements.tree.Tree;

/**
 * Binary search tree implementation.
 */
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    /** Node of binary tree. Holds references to left and right children. */
    class BinaryNode extends Node<T> {
        protected BinaryNode left;
        protected BinaryNode right;

        int size;

        BinaryNode(T data) {
            super(data);
            this.size = 1;
        }
    }

    /** Root of binary tree. <code>null</code> if tree is empty */
    BinaryNode root;

    /**
     * Find node by key.
     * 
     * @param key
     * @return
     */
    public T find(T key) {
        Node<T> node = getNode(key);
        return node != null ? node.data : (T) null;
    }

    @Override
    public boolean contains(T key) {
        return getNode(key) != null;
    }

    /**
     * 
     * @param key
     * @return
     */
    BinaryNode getNode(T key) {
        if (root == null) {
            return null;
        }

        BinaryNode current = root;

        while (current.data.compareTo(key) != 0) {
            if (current.data.compareTo(key) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }

            if (current == null) {
                return null;
            }
        }

        return current;
    }

    /**
     * 
     * @param key
     */
    @Override
    public void insert(T key) {
        if (find(key) != null) {
            return;
        }

        if (root == null) {
            root = new BinaryNode(key);
        } else {
            BinaryNode current = root;

            while (true) {
                current.size++;
                if (current.data.compareTo(key) > 0) {
                    if (current.left == null) {
                        current.left = new BinaryNode(key);
                        return;
                    }
                    current = current.left;
                } else if (current.data.compareTo(key) < 0) {
                    if (current.right == null) {
                        current.right = new BinaryNode(key);
                        return;
                    }
                    current = current.right;
                }
            }
        }
    }

    /**
     * 
     * @param key
     */
    @Override
    public void delete(T key) {
        if (root == null) {
            return;
        }

        BinaryNode parent = null;
        BinaryNode current = root;

        while (current.data.compareTo(key) != 0) {
            parent = current;

            if (current.data.compareTo(key) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }

            if (current == null) {
                return;
            }
        }

        if (current.left == null && current.right == null) {
            substituteNodes(current, parent, null);
        } else if (current.left != null && current.right == null) {
            substituteNodes(current, parent, current.left);
        } else if (current.left == null && current.right != null) {
            substituteNodes(current, parent, current.right);
        } else if (current.left != null && current.right != null) {
            BinaryNode sucParent = current;
            BinaryNode suc = current.right;

            while (suc.left != null) {
                sucParent = suc;
                suc = suc.left;
            }

            if (current != sucParent) {
                sucParent.left = suc.right;
                suc.right = current.right;
            }

            suc.left = current.left;

            substituteNodes(current, parent, suc);
        }
    }

    /**
     * Tree nodes count.
     * 
     * @return Number of nodes in tree.
     */
    @Override
    public int size() {
        return root == null ? 0 : root.size;
    }

    public int depth() {
        return depth(root);
    }

    // =============================================================================

    private int depth(BinaryNode node) {

        if (node == null)
            return 0;

        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    /**
     * 
     * @param node
     * @return
     */
    protected int count(BinaryNode node) {
        return node == null ? 0 : 1 + count(node.left) + count(node.right);
    }

    /**
     * 
     * @param node
     * @param parent
     * @param newNode
     */
    private void substituteNodes(BinaryNode node, BinaryNode parent, BinaryNode newNode) {
        if (parent != null) {
            if (parent.left == node) {
                parent.left = newNode;
            } else if (parent.right == node) {
                parent.right = newNode;
            }
        } else {
            root = newNode;
        }

        if (root != null) root.size--;
        node = null;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();

        binarySearchTree.insert(2);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(5);
        binarySearchTree.insert(0);

        System.out.println(binarySearchTree.root.right.size);
    }
}
