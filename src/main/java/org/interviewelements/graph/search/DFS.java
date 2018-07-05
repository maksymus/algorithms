package org.interviewelements.graph.search;

import org.interviewelements.graph.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Depth first search.
 */
public class DFS extends Search {

    private Deque<Integer> queue = new LinkedList<Integer>();

    public DFS(Graph graph) {
        super(graph);
    }

    @Override
    protected void put(int x) {
        queue.push(x);
    }

    @Override
    protected int get() {
        return queue.pop();
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

        DFS dfs = new DFS(graph);
        dfs.setVisitor(x -> System.out.println(x));

        boolean hasPath = dfs.search(0, 5);

        System.out.println(hasPath);
    }
}