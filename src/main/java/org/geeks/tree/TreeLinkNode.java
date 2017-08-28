package org.geeks.tree;

public class TreeLinkNode extends TreeNode {
    TreeNode next;

    public TreeLinkNode(int x) {
        super(x);
    }

    public TreeLinkNode(int val, TreeLinkNode left, TreeLinkNode right) {
        super(val, left, right);
    }

    public static TreeLinkNode build(int val, TreeLinkNode left, TreeLinkNode right) {
        return new TreeLinkNode(val, left, right);
    }

    public static TreeLinkNode build(int val) {
        return new TreeLinkNode(val);
    }

}
