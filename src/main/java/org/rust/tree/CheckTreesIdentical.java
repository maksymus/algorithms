package org.rust.tree;

import java.util.Objects;

public class CheckTreesIdentical {
    //25, 50, 100, 125, 200, 250
    public static void main(String[] args) {
        Node tree1 = new Node(125, new Node(100), new Node(200));
        Node tree2 = new Node(125, new Node(100), new Node(200));

        boolean result = isIdentical(tree1, tree2);
        System.out.println(result);
    }

    public static boolean isIdentical(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        if (!Objects.equals(node1.data, node2.data))
            return false;

        return isIdentical(node1.left, node2.left) &&
                isIdentical(node1.right, node2.right);
    }
}
