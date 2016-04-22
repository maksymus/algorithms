package org.interviewelements.tree.walker;

import algorithm.tree.walker.Tree.Node;

public class ReverseTreeWalker implements TreeWalker {

    @Override
    public void walk(Tree<?> tree) {
        System.out.print(this.getClass().getSimpleName() + ":\t");

        walk(tree.getRoot());
        System.out.println();
    }

    private void walk(Node<?> node) {
        for (Node<?> leaves : node.getLeaves()) {
            walk(leaves);
        }
        System.out.print(node.getData() + " ");
    }
}
