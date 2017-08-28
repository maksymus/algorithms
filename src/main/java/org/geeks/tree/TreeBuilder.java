package org.geeks.tree;

import org.geeks.list.ListNode;

public class TreeBuilder {

    private ListNode current;

    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public TreeNode construct(ListNode list) {
        current = list;
        return construct(0, list.length() - 1);
    }

    public TreeNode construct(int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;

        TreeNode left = construct(start, mid - 1);
        TreeNode tree = new TreeNode(current.val);
        current = current.next;
        TreeNode right = construct(mid + 1, end);

        tree.left = left;
        tree.right = right;

        return tree;
    }

    /**
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public TreeNode construct(int[] arr) {
        return construct(arr, 0, arr.length - 1);
    }

    public TreeNode construct(int[] arr, int start, int end) {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;

        return new TreeNode(arr[mid], construct(arr, start, mid - 1), construct(arr, mid + 1, end));
    }

    /**
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * in-order:   4 2 5 (1) 6 7 3  8
     * post-order: 4 5 2  6  7 8 3 (1)
     *
     *       1
     *    /    \
     *   2      3
     *  / \    / \
     * 4   5  7  8
     *       /
     *       6
     */
    public TreeNode constructInPost(int[] inorder, int[] postorder) {
        return constructInPost(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode constructInPost(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return null;

        int val = postorder[postEnd];

        int inSplit = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == val) {
                inSplit = i;
                break;
            }
        }

        int postSplit = postStart + (inSplit - inStart);

        TreeNode treeNode = new TreeNode(val);
        treeNode.left  = constructInPost(inorder, inStart,     inSplit - 1, postorder, postStart + 1, postSplit);
        treeNode.right = constructInPost(inorder, inSplit + 1, inEnd,       postorder, postSplit + 1, postEnd - 1);

        return treeNode;
    }

    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * in-order:   4 2 5 (1) 6 7 3 8
     * pre-order: (1) 2 4 5  3 7 6 8
     *
     *       1
     *    /    \
     *   2      3
     *  / \    / \
     * 4   5  7  8
     *       /
     *       6
     */
    public TreeNode constructInPre(int[] inorder, int[] preorder) {
        return constructInPre(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    public TreeNode constructInPre(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd) {
        if (inStart > inEnd || preStart > preEnd)
            return null;

        int val = preorder[preStart];

        int inSplit = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == val) {
                inSplit = i;
                break;
            }
        }

        int preSplit = preStart + (inSplit - inStart);

        TreeNode treeNode = new TreeNode(val);
        treeNode.left  = constructInPre(inorder, inStart,     inSplit - 1, preorder, preStart + 1, preSplit);
        treeNode.right = constructInPre(inorder, inSplit + 1, inEnd,       preorder, preSplit + 1, preEnd);

        return treeNode;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeBuilder().construct(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(root);

        ListNode list = ListNode.build(new int[] {1, 2, 3, 4, 5, 6, 7});
        TreeNode root1 = new TreeBuilder().construct(list);
        System.out.println(root1);
    }
}
