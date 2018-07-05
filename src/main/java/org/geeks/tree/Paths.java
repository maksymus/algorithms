package org.geeks.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return all root-to-leaf paths.
 */
public class Paths {
    private Depth depth = new Depth();

    /**
     * Get all root-to-leaf paths.
     */
    public List<List<Integer>> paths(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null)
            return lists;

        int maxDepth = depth.max(root);
        int[] paths = new int[maxDepth];

        paths(root, paths, 0, lists);

        return lists;
    }

    private void paths(TreeNode node, int[] paths, int depth, List<List<Integer>> lists) {
        if (node == null)
            lists.add(Arrays.stream(paths).boxed().collect(Collectors.toList()));

        paths[depth] = node.val;

        paths(node.left, paths, depth + 1, lists);
        paths(node.right, paths, depth + 1, lists);
    }

    /**
     * Print all Possible Paths
     */
    public void printPaths(TreeNode root) {
        printPaths(root, new Object[depth.max(root)], 0);
    }

    private void printPaths(TreeNode node, Object[] objs, int length) {
        if (node == null)
            return;

        objs[length++] = node.val;

        if (node.left == null && node.right == null) {
            printPath(objs, length);
        } else {
            printPaths(node.left, objs, length);
            printPaths(node.right, objs, length);
        }
    }

    private static void printPath(Object[] objs, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(objs[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}
