package org.interviewelements.graph.search;

import algorithm.graph.Graph;
import algorithm.graph.Graph.AdjList;
import algorithm.graph.Graph.Edge;

public abstract class Search {

    private Graph graph;
    private Visitor visitor;

    private boolean[] visited;

    public Search(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getVCount()];
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public boolean search(int a, int z) {

        put(a);

        while (!empty()) {
            int x = get();

            if (visited[x])
                continue;
            else
                visited[x] = true;

            if (visitor != null)
                visitor.visit(x);

            if (x == z)
                return true;

            AdjList adjs = graph.getAdjacencies(x);

            for (Edge edge : adjs) {
                put(edge.to());
            }
        }

        return false;
    }

    protected abstract void put(int x);

    protected abstract int get();

    protected abstract boolean empty();
}
