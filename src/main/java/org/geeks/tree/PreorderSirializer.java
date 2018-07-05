package org.geeks.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *          9
 *        /   \
 *       3     2
 *      / \   / \
 *     4   1  #  6
 *    / \ / \   / \
 *    # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * Given a string of comma separated values, verify whether it is a correct preorder traversal sedfdrialization of a binary tree.
 *
 * Find an algorithm without reconstructing the tree.
 */
public class PreorderSirializer {

    public static final String NULL_NODE = "#";

    public String serialize(TreeNode root) {
        if (root == null)
            return NULL_NODE;

        List<String> values = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                values.add(NULL_NODE);
            } else {
                values.add(String.valueOf(node.val));
                stack.push(node.right);
                stack.push(node.left);
            }
        }

        return values.stream().collect(Collectors.joining(","));
    }

    public TreeNode deserialize(String preorder) {
        String[] elems = preorder.split(",");
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode root = null;

        for (int i = 0; i < elems.length; i++) {
            String elem = elems[i];

            if (NULL_NODE.equals(elem)) {
                if (i > 0 && NULL_NODE.equals(elems[i-1])) {
                    stack.pop();
                }
                continue;
            }

            TreeNode node = new TreeNode(Integer.valueOf(elem));

            if (i == 0) {
                root = node;
            } else {
                if (!NULL_NODE.equals(elems[i-1])) {
                    stack.peek().left = node;
                } else {
                    stack.pop().right = node;
                }
            }

            stack.push(node);
        }

        return root;
    }

    public boolean isValid(String preorder) {
        String[] elems = preorder.split(",");
        LinkedList<String> stack = new LinkedList<>();

        for (int i = 0; i < elems.length; i++) {
            stack.push(elems[i]);

            while (stack.size() > 2
                    && NULL_NODE.equals(stack.get(0))
                    && NULL_NODE.equals(stack.get(1))
                    && !NULL_NODE.equals(stack.get(2))) {
                stack.pop();
                stack.pop();
                stack.pop();

                stack.push(NULL_NODE);
            }
        }

        return stack.size() == 1 && NULL_NODE.equals(stack.peek());
    }

    public static void main(String[] args) {
        PreorderSirializer preorderSirializer = new PreorderSirializer();
        boolean valid = preorderSirializer.isValid("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println(valid);

        TreeNode deserialize = preorderSirializer.deserialize("9,3,4,#,#,1,#,#,2,#,6,#,#");
        String serialize = preorderSirializer.serialize(deserialize);
        System.out.println(serialize);
    }
}
