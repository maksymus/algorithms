package org.interviewelements.tree.walker;


import java.util.List;

public class SymetricTreeWalker implements TreeWalker {

    @Override
    public void walk(Tree<?> tree) {
        System.out.print(this.getClass().getSimpleName() + ":\t");

        walk(tree.getRoot());
        System.out.println();
    }

    private void walk(Tree.Node<?> node) {
        List<?> leaves = node.getLeaves();

        if (!leaves.isEmpty()) {
            walk((Tree.Node<?>) leaves.get(0));
        }

        System.out.print(node.getData() + " ");

        for (int i = 1; i < leaves.size(); i++) {
            walk((Tree.Node<?>) leaves.get(i));
        }
    }
}
