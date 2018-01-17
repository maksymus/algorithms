package org.cracking.graph;

import java.util.*;
import java.util.stream.Collectors;

import static org.cracking.graph.Graph.Node;

/**
 * You are given a list of projects and a list of dependencies (which is a list of pairs of
 * projects, where the second project is dependent on the first project). All of a project's dependencies
 * must be built before the project is. Find a build order that will allow the projects to be built. If there
 * is no valid build order, return an error.
 * EXAMPLE
 * Input:
 *      projects: a, b, c, d, e, f
 *      dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 */
public class BuildOrder {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(true);

        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");


        graph.add(a, b, c, d, e, f);

        graph.join(a, d);
        graph.join(f, b);
        graph.join(b, d);
        graph.join(f, a);
        graph.join(d, c);

        new BuildOrder().order(graph).forEach(node -> System.out.println(node.getValue()));
    }

    //    // topological sort
    public List<Node> order(Graph graph) {
        if (hasCycle(graph))
            throw new RuntimeException("can't do topological sort - graph has cycles");

        Deque<Node> stack = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        List<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            order(node, visited, stack);
        }

        return stack.stream().collect(Collectors.toList());
    }

    public void order(Node node, Set<Node> visited, Deque<Node> stack) {
        if (visited.contains(node))
            return;

        visited.add(node);

        List<Node> adjacents = node.getAdjacents();
        for(Node adjacent : adjacents) {
            order(adjacent, visited, stack);
        }

        stack.push(node);
    }

    private boolean hasCycle(Graph graph) {
        Set<Node> visited = new HashSet<>();

        List<Node> nodes = graph.getNodes();
        for (Node node : nodes) {
            if (hasCycle(node, visited, new HashSet<>())) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> visited, Set<Node> frame) {
        if (visited.contains(node))
            return false;

        visited.add(node);
        frame.add(node);

        List<Node> adjacents = node.getAdjacents();
        for(Node adjacent : adjacents) {
            if (hasCycle(adjacent, visited, frame)) {
                return true;
            } else if (frame.contains(adjacent)) {
                return true;
            }
        }

        frame.remove(node);

        return false;
    }
}
