package org.geeks.tree;

import java.util.Objects;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 */
public class SymmetricTree {
    public boolean isSimmertic(TreeNode root) {
        if (root == null)
            return true;

        return isSimmertic(root.left, root.right);
    }

    public boolean isSimmertic(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;

        if (left == null || right == null)
            return false;

        if (!Objects.equals(left.val, right.val))
            return false;

        return isSimmertic(left.left, right.right) && isSimmertic(left.right, right.left);
    }
}
