package org.cracking.graph;

import java.util.*;
import java.util.stream.Collectors;

import static org.cracking.graph.Tree.Node;

/**
 * List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
 * at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 */
public class DepthList {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1, new Node(2), new Node(3));
        List<List<Node>> res = new DepthList().build(root);

        res.forEach(list -> {
            list.forEach(elem -> System.out.print(elem + ","));
            System.out.println();
        });
    }

    public List<List<Node>> build(Node node) {
        Map<Node, Integer> depths = new HashMap<>();
        Map<Integer, List<Node>> lists = new HashMap<>();

        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        depths.put(node, 1);

        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
            Integer depth = depths.get(tmp);

            lists.putIfAbsent(depth, new ArrayList<>());
            lists.get(depth).add(tmp);

            Node left = tmp.getLeft();
            if (left != null) {
                queue.add(left);
                depths.put(left, depth + 1);
            }

            Node right = tmp.getRight();
            if (right != null) {
                queue.add(right);
                depths.put(right, depth + 1);
            }
        }

        return lists.values().stream().collect(Collectors.toList());
    }
}
