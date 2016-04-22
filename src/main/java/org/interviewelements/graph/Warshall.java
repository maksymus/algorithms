package org.interviewelements.graph;


public class Warshall {
    private boolean[][] closure;

    public Warshall(Graph graph) {

        int vCount = graph.getVCount();
        this.closure = new boolean[vCount][vCount];

        double[][] matrix = graph.createMatrix();

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                if (Double.isInfinite(matrix[i][j])) {
                    closure[i][j] = false;
                } else {
                    closure[i][j] = true;
                }
            }
        }

        for (int k = 0; k < vCount; k++) {
            for (int i = 0; i < vCount; i++) {
                for (int j = 0; j < vCount; j++) {
                    closure[i][j] = closure[i][j] || (closure[i][k] && closure[k][j]);
                }
            }
        }
    }

    public boolean isReachable(int a, int z) {
        return closure[a][z];
    }

    public static void main(String[] args) {
        Graph g = new Graph(true, 6);

        g.add(new Graph.Edge(0, 1, 1));
        g.add(new Graph.Edge(1, 2, 1));
        g.add(new Graph.Edge(1, 3, 3));
        g.add(new Graph.Edge(2, 3, 1));
        g.add(new Graph.Edge(3, 4, 1));
        g.add(new Graph.Edge(0, 4, 5));
        g.add(new Graph.Edge(5, 0, 5));

        Warshall warshall = new Warshall(g);
        System.out.println(warshall.isReachable(5, 0));
        System.out.println(warshall.isReachable(0, 5));

    }
}
