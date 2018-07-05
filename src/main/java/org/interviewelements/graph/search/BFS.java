package org.interviewelements.graph.search;

import org.interviewelements.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Breadth first search implementation.
 */
public class BFS extends Search {

    private Queue<Integer> queue = new LinkedList<Integer>();

    public BFS(Graph graph) {
        super(graph);
    }

    @Override
    protected void put(int x) {
        queue.add(x);
    }

    @Override
    protected int get() {
        return queue.poll();
    }

    @Override
    protected boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.add(new Graph.Edge(0, 2));
        graph.add(new Graph.Edge(2, 1));
        graph.add(new Graph.Edge(2, 3));
        graph.add(new Graph.Edge(1, 4));
        graph.add(new Graph.Edge(1, 5));
        graph.add(new Graph.Edge(4, 5));

        BFS bfs = new BFS(graph);
        bfs.setVisitor(x -> System.out.println(x));

        boolean hasPath = bfs.search(0, 5);

        System.out.println(hasPath);
    }
}