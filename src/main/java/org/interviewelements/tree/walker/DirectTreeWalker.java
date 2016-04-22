package org.interviewelements.tree.walker;

import algorithm.tree.walker.Tree.Node;

public class DirectTreeWalker implements TreeWalker {

    @Override
    public void walk(Tree<?> tree) {
        System.out.print(this.getClass().getSimpleName() + ":\t");

        walk(tree.getRoot());
        System.out.println();
    }

    private void walk(Node<?> node) {
        System.out.print(node.getData() + " ");
        for (Node<?> leaves : node.getLeaves()) {
            walk(leaves);
        }
    }
}
