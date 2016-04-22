package org.interviewelements.graph;

import algorithm.graph.Graph.AdjList;
import algorithm.graph.Graph.Edge;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijkstra algorithm realization. Search for shortest path between given vertex
 * and all the rest vertexes.
 */
public class Dijkstra {

    private class PQComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Double.valueOf(weights[o1]).compareTo(Double.valueOf(weights[o2]));
        }
    };

    private double[] weights;
    private Edge[] shortestPathTree;

    public Dijkstra(Graph graph, int s) {
        int vertexCount = graph.getVCount();
        weights = new double[vertexCount];
        shortestPathTree = new Edge[vertexCount];

        for (int v = 0; v < vertexCount; v++)
            weights[v] = Double.MAX_VALUE;
        weights[s] = 0.0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(vertexCount, new PQComparator());

        for (int v = 0; v < vertexCount; v++)
            priorityQueue.add(v);

        while (!priorityQueue.isEmpty()) {
            int v = priorityQueue.remove();
            if (v != s && shortestPathTree[v] == null)
                return;

            AdjList adjacencies = graph.getAdjacencies(v);
            for (Edge edge : adjacencies) {
                int w = edge.to();
                double p = weights[v] + edge.weight();
                if (p < weights[w]) {
                    priorityQueue.remove(w);
                    weights[w] = p;
                    shortestPathTree[w] = edge;
                    priorityQueue.add(w);
                }
            }
        }
    }

    public Edge pathR(int v) {
        return shortestPathTree[v];
    }

    public double dist(int v) {
        return weights[v];
    }

    public static void main(String[] args) {
        Graph g = new Graph(true, 7);

        g.add(new Edge(3, 2, 12));
        g.add(new Edge(3, 1, 1.1));
        g.add(new Edge(1, 4, 2.3));
        g.add(new Edge(2, 4, 2.5));
        g.add(new Edge(4, 5, 1.5));
        g.add(new Edge(5, 6, 1.9));
        g.add(new Edge(6, 2, 2.1));

        Dijkstra dijkstra = new Dijkstra(g, 3);

        System.out.println(dijkstra.dist(2));

        for (Edge e = dijkstra.pathR(2); e != null; e = dijkstra.pathR(e.from())) {
            System.out.println(e.from());
        }
    }
}
