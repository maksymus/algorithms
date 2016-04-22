package org.interviewelements.tree;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 2-3 Tree implementation.
 */
public class BTree<T extends Comparable<T>> extends Tree<T> {

    /**
	 * 
	 */
    class InternalNode {
        private T first;
        private T second;

        public InternalNode(T element) {
            this.first = element;
            this.second = (T) null;
        }

        InternalNode(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public void add(T element) {
            if (full())
                throw new IllegalStateException("Node has two elements. Can't add third.");

            if (first == null)
                first = element;
            else if (element.compareTo(first) >= 0)
                second = element;
            else {
                second = first;
                first = element;
            }
        }

        public boolean full() {
            return first != null && second != null;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    /**
	 * 
	 */
    public class BTreeNode extends Node<InternalNode> {

        public BTreeNode(T data, BTreeNode parent) {
            super(new InternalNode(data));
            this.parent = parent;
        }

        BTreeNode(T first, T second, BTreeNode parent) {
            super(new InternalNode(first, second));
            this.parent = parent;
        }

        BTreeNode parent;

        BTreeNode child1;
        BTreeNode child2;
        BTreeNode child3;

        BTreeNode phantom;

        public boolean isLeaf() {
            return child1 == null && child2 == null && child3 == null;
        }

        public boolean isStuffed() {
            return child1 != null && child2 != null && child3 != null;
        }

        public boolean isFull() {
            return data.full();
        }

        public void add(T element) {
            data.add(element);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BTree.BTreeNode)) {
                return false;
            }

            if (this == obj) {
                return true;
            }

            BTreeNode rhs = (BTree.BTreeNode) obj;

            return new EqualsBuilder().append(this.child1, rhs.child1).append(this.child2, rhs.child2)
                    .append(this.child3, rhs.child3).append(this.data, rhs.data).isEquals();
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public String toString() {
            return data.first + "," + data.second;
        }
    }

    BTreeNode root;

    @Override
    public void delete(T key) {
    }

    @Override
    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode(key, null);
        } else {
            BTreeNode leaf = findLeaf(key, root);
            insert(key, leaf);
        }
    }

    private void insert(T key, BTreeNode leaf) {
        if (!leaf.isFull()) {
            leaf.add(key);
        } else {
            if (leaf.parent == null)
                leaf.parent = root = new BTreeNode((T) null, null);

            if (key.compareTo(leaf.data.first) <= 0) {
                split(leaf, key, leaf.data.first, leaf.data.second);
            } else if (key.compareTo(leaf.data.second) > 0) {
                split(leaf, leaf.data.first, leaf.data.second, key);
            } else {
                split(leaf, leaf.data.first, key, leaf.data.second);
            }
        }
    }

    private void split(BTreeNode node, T left, T middle, T right) {
        BTreeNode leftNode = new BTreeNode(left, node.parent);
        BTreeNode rightNode = new BTreeNode(right, node.parent);

        leftNode.child1 = node.child1;
        leftNode.child2 = node.child2;

        rightNode.child1 = node.child3;
        rightNode.child2 = node.phantom;

        fixParentRef(leftNode);
        fixParentRef(rightNode);

        if (node.parent.child1 == node) {
            node.parent.phantom = node.parent.child3;
            node.parent.child3 = node.parent.child2;
            node.parent.child2 = rightNode;
            node.parent.child1 = leftNode;
        } else if (node.parent.child2 == node) {
            node.parent.phantom = node.parent.child3;
            node.parent.child3 = rightNode;
            node.parent.child2 = leftNode;
        } else if (node.parent.child3 == node) {
            node.parent.phantom = rightNode;
            node.parent.child3 = leftNode;
        } else {
            node.parent.child2 = rightNode;
            node.parent.child1 = leftNode;
        }

        insert(middle, node.parent);
    }

    private void fixParentRef(BTreeNode node) {
        if (node.child1 != null)
            node.child1.parent = node;
        if (node.child2 != null)
            node.child2.parent = node;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(T key) {
        return find(key, root) != null;
    }

    private BTreeNode find(T key, BTreeNode node) {

        if (node == null)
            return null;

        T first = node.data.first;
        T second = node.data.second;

        if (key.equals(first) || key.equals(second)) {
            return node;
        }

        if (key.compareTo(first) < 0) {
            return find(key, node.child1);
        }

        if (second == null || key.compareTo(second) < 0) {
            return find(key, node.child2);
        }

        return find(key, node.child3);
    }

    private BTreeNode findLeaf(T key, BTreeNode node) {

        if (node.isLeaf())
            return node;

        T first = node.data.first;
        T second = node.data.second;

        if (key.compareTo(first) < 0)
            return findLeaf(key, node.child1);

        if (second == null || key.compareTo(second) < 0)
            return findLeaf(key, node.child2);

        return findLeaf(key, node.child3);
    }

    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<Integer>();
        btree.insert(1);
        btree.insert(3);
        System.out.println(btree);

        btree.insert(2);
        System.out.println(btree);

    }
}
