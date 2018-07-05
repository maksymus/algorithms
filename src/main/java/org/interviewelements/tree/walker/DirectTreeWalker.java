package org.interviewelements.tree.walker;

public class DirectTreeWalker implements TreeWalker {

    @Override
    public void walk(Tree<?> tree) {
        System.out.print(this.getClass().getSimpleName() + ":\t");

        walk(tree.getRoot());
        System.out.println();
    }

    private void walk(Tree.Node<?> node) {
        System.out.print(node.getData() + " ");
        for (Tree.Node<?> leaves : node.getLeaves()) {
            walk(leaves);
        }
    }
}
