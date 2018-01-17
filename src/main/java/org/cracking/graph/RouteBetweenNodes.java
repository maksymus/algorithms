package org.cracking.graph;

import java.util.*;

import static org.cracking.graph.Graph.Node;

/**
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 */
public class RouteBetweenNodes {
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);

        graph.add(node1, node2, node3);

        graph.join(node1, node2);
        graph.join(node2, node3);
        graph.join(node3, node2);

        System.out.println(hasRoute(node1, node2));
        System.out.println(hasRoute(node1, node3));
        System.out.println(hasRoute(node3, node1));
    }

    public static boolean hasRoute(Node node1, Node node2) {
        Map<Node, Boolean> visited = new HashMap<>();

        Deque<Node> nodes = new LinkedList<>();
        nodes.add(node1);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            if (visited.putIfAbsent(node, true) != null)
                continue;

            if (node == node2)
                return true;

            List adjacent = node.getAdjacents();
            nodes.addAll(adjacent);
        }

        return false;
    }
}
