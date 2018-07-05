package org.cracking.graph;

import java.util.Objects;

import static org.cracking.graph.Tree.Node;

/**
 * Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
 * algorithm to determine ifT2 is a subtree of Tl.
 * A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
 * That is, if you cut off the tree at node n, the two trees would be identical.
 */
public class CheckSubtree {
    public static void main(String[] args) {

    }

    public boolean isSubtree(Node root1, Node root2) {
        if (root1 == null)
            return false;

        if (root2 == null)
            return true;

        if (treeMatch(root1, root2))
            return true;

        return isSubtree(root1.getLeft(), root2) || isSubtree(root1.getRight(), root2);
    }

    private boolean treeMatch(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        if (!Objects.equals(node1.getValue(), node2.getValue()))
            return false;

        return treeMatch(node1.getLeft(), node2.getLeft())
                && treeMatch(node1.getRight(), node2.getRight());
    }
}
