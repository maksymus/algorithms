package org.interviewelements.graph;

import algorithm.graph.Graph.Edge;

/**
 * Floyd algorithm implementation. Shortest path between all vertexes.
 */
public class Floyd {
    private Graph graph;
    private double[][] matrix;
    private int[][] path;

    public Floyd(Graph graph) {
        this.graph = graph;

        int vCount = graph.getVCount();

        matrix = graph.createMatrix();
        path = new int[graph.getVCount()][graph.getVCount()];

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                if (Double.isInfinite(matrix[i][j])) {
                    path[i][j] = -1;
                } else {
                    path[i][j] = i;
                }
            }
        }

        // Floyd algorithm
        for (int k = 0; k < vCount; k++) {
            for (int i = 0; i < vCount; i++) {
                for (int j = 0; j < vCount; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    public double dist(int a, int z) {
        return matrix[a][z];
    }

    public Edge pathR(int a, int z) {
        return graph.getEdge(path[a][z], z);
    }

    public static void main(String[] args) {
        Graph g = new Graph(false, 5);

        g.add(new Edge(0, 1, 1));
        g.add(new Edge(1, 2, 1));
        g.add(new Edge(1, 3, 3));
        g.add(new Edge(2, 3, 1));
        g.add(new Edge(3, 4, 1));
        g.add(new Edge(0, 4, 5));

        Floyd floyd = new Floyd(g);

        System.out.println(floyd.dist(0, 4));

        for (Edge e = floyd.pathR(0, 4); e != null; e = floyd.pathR(0, e.from())) {
            System.out.println(e.from() + " " + e.to());
        }
    }

}
