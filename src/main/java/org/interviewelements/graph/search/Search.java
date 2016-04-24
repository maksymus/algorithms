package org.interviewelements.graph.search;

import org.interviewelements.graph.Graph;

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

            Graph.AdjList adjs = graph.getAdjacencies(x);

            for (Graph.Edge edge : adjs) {
                put(edge.to());
            }
        }

        return false;
    }

    protected abstract void put(int x);

    protected abstract int get();

    protected abstract boolean empty();
}
