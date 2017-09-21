package org.saurabhschool

import org.util.BinTreeNode
import spock.lang.Shared
import spock.lang.Specification

/**
 *      node1           node2              node5          node9
 *      /  \            /  \               /  \           /   \
 *                 node3    node4     node6   node7          node10
 *                                            /              /  \
 *                                          node8       node11  node12
 */
class BinTreeTest extends Specification {
    @Shared def node12 = new BinTreeNode(12)
    @Shared def node11 = new BinTreeNode(11)
    @Shared def node10 = new BinTreeNode(10, node11, node12)
    @Shared def node9 = new BinTreeNode(9, null, node10)
    @Shared def node8 = new BinTreeNode(8)
    @Shared def node7 = new BinTreeNode(7, node8, null)
    @Shared def node6 = new BinTreeNode(6)
    @Shared def node5 = new BinTreeNode(5, node6, node7)
    @Shared def node4 = new BinTreeNode(4)
    @Shared def node3 = new BinTreeNode(3)
    @Shared def node2 = new BinTreeNode(2, node3, node4)
    @Shared def node1 = new BinTreeNode(1, null, null)

    def "test isBalanced"() {
        expect:
        BinTree.isBalanced(node) == result

        where:
        node                || result
        node1               || true
        node2               || true
        node5               || true
        node9               || false
    }

    def "test lowestCommonAncestor"() {
        expect:
        BinTree.lowestCommonAncestor(root, a, b) == node

        where:
        root    | a      | b      || node
//        node2   | node3  | node4  || node2
        node9   | node11 | node12 || node10
    }
}