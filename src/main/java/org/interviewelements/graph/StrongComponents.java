package org.interviewelements.graph;

import org.interviewelements.graph.Graph.Edge;

import java.util.Arrays;

/**
 * Strongly connected component algorithm implementation. Kosaraju's algorithm.
 */
public class StrongComponents {

    private interface Action {
        public void fire(int vertex);
    }

    private final Graph graph;
    private final Graph reverseGraph;

    private int[] finishingTime;
    private int finishingOrder;

    private int[] clusters;
    private int cluster;

    private boolean[] visited;

    public StrongComponents(Graph graph) {
        this.graph = graph;
        this.reverseGraph = graph.reverse();
        this.finishingTime = new int[graph.getVCount()];
        this.clusters = new int[graph.getVCount()];
        this.visited = new boolean[graph.getVCount()];
    }

    /**
     * Compute strongly connected components.
     */
    public void compute() {

        // dfs on reversed graph. remember each node finishing time
        for (int i = 0; i < reverseGraph.getVCount(); i++) {
            if (!visited[i]) {
                dfs(reverseGraph, i, new Action() {
                    @Override
                    public void fire(int vertex) {
                        finishingTime[finishingOrder++] = vertex;
                    }
                });
            }
        }

        Arrays.fill(visited, false);

        // dfs on graph. each iteration increases cluster number (new cluster is
        // found)
        for (int i = finishingOrder - 1; i >= 0; i--) {
            int v = finishingTime[i];

            if (!visited[v]) {
                dfs(graph, v, new Action() {
                    @Override
                    public void fire(int vertex) {
                        clusters[vertex] = cluster;
                    }
                });

                cluster++;
            }
        }
    }

    /**
     * Depth first search.
     * 
     * @param g
     *            Graph.
     * @param v
     *            Vertex.
     * @param action
     *            Action to be fired when tree end is reached.
     */
    private void dfs(Graph g, int v, Action action) {
        visited[v] = true;

        for (Edge edge : g.getAdjacencies(v)) {
            if (!visited[edge.to()]) {
                dfs(g, edge.to(), action);
            }
        }

        action.fire(v);
    }

    public static void main(String[] args) {
        Graph g = new Graph(true, 13);
        g.add(new Edge(0, 1));
        g.add(new Edge(0, 5));
        g.add(new Edge(0, 6));
        g.add(new Edge(2, 0));
        g.add(new Edge(2, 3));
        g.add(new Edge(3, 2));
        g.add(new Edge(3, 5));
        g.add(new Edge(4, 2));
        g.add(new Edge(4, 3));
        g.add(new Edge(4, 11));
        g.add(new Edge(5, 4));
        g.add(new Edge(6, 4));
        g.add(new Edge(6, 9));
        g.add(new Edge(7, 6));
        g.add(new Edge(7, 8));
        g.add(new Edge(8, 7));
        g.add(new Edge(8, 9));
        g.add(new Edge(9, 10));
        g.add(new Edge(9, 11));
        g.add(new Edge(10, 12));
        g.add(new Edge(11, 12));
        g.add(new Edge(12, 9));

        StrongComponents strongComponents = new StrongComponents(g);
        strongComponents.compute();

        int[] clust = strongComponents.clusters;

        for (int vertex = 0; vertex < g.getVCount(); vertex++) {
            System.out.println(vertex + ":" + clust[vertex]);
        }
    }
}
