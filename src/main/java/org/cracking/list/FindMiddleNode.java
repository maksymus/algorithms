package org.cracking.list;

public class FindMiddleNode {

    public static void main(String[] args) {
        Node root = Node.build(1, 2, 3, 4, 5, 6, 7, 8);
        Node middle = new FindMiddleNode().find(root);

        System.out.println(middle);
    }

    public Node find(Node root) {
        if (root == null)
            return null;

        Node node = root;
        Node runner = root;

        while (runner != null && runner.next != null) {
            node = node.next;
            runner = runner.next.next;
        }

        return node;
    }
}
