package org.interviewelements.graph;

import java.util.Iterator;

/**
 * Graph ADT. Linked list based implementation.
 */
public class Graph {

    /**
     * Linked list to keep adjacent nodes.
     */
    private static class Node {
        private int v;
        private double weight;
        private Node next;

        public Node(int v, Node next, double weight) {
            this.v = v;
            this.weight = weight;
            this.next = next;
        }
    }

    /**
	 * 
	 */
    public class AdjList implements Iterable<Edge> {

        private int nodeNum;

        public AdjList(int i) {
            this.nodeNum = i;
        }

        @Override
        public Iterator<Edge> iterator() {

            return new Iterator<Edge>() {

                private int rootIndex;
                private Node curr;

                {
                    this.rootIndex = nodeNum;
                    this.curr = nodes[nodeNum];
                }

                @Override
                public boolean hasNext() {
                    return curr != null;
                }

                @Override
                public Edge next() {
                    Edge edje = new Edge(rootIndex, curr.v, curr.weight);
                    curr = curr.next;
                    return edje;
                }

                @Override
                public void remove() {
                    throw new RuntimeException("Not supported.");
                }
            };
        }
    }

    /**
     * Graph edge.
     */
    public static class Edge {
        private int v, w;
        private double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public Edge(int v, int w) {
            this(v, w, 0);
        }

        public double weight() {
            return weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }
    }

    private boolean directional;
    private Node[] nodes;
    private int vCount;
    private int eCount;

    public Graph(int size) {
        this(false, size);
    }

    public Graph(boolean directional, int size) {
        this.directional = directional;
        this.nodes = new Node[size];
        this.vCount = size;
    }

    public void add(Edge edje) {
        nodes[edje.v] = new Node(edje.w, nodes[edje.v], edje.weight);
        if (!directional)
            nodes[edje.w] = new Node(edje.v, nodes[edje.w], edje.weight);
        eCount++;
    }

    public AdjList getAdjacencies(int i) {
        return new AdjList(i);
    }

    public int getVCount() {
        return vCount;
    }

    public int getECount() {
        return eCount;
    }

    public Edge getEdge(int a, int z) {
        for (Edge edge : getAdjacencies(a)) {
            if (edge.to() == z)
                return edge;
        }

        return null;
    }

    public Graph reverse() {
        if (!directional)
            return this;

        Graph reversed = new Graph(this.directional, this.vCount);

        for (int i = 0; i < vCount; i++) {
            for (Edge edge : this.getAdjacencies(i)) {
                reversed.add(new Edge(edge.w, edge.v, edge.weight));
            }
        }

        return reversed;
    }

    public double[][] createMatrix() {
        double[][] matrix = new double[vCount][vCount];

        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                matrix[i][j] = Double.POSITIVE_INFINITY;

                if (i == j)
                    matrix[i][j] = 0;
            }

            for (Edge edge : getAdjacencies(i)) {
                matrix[i][edge.w] = edge.weight;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Graph g = new Graph(30);
        g.add(new Edge(0, 1, 1.1));
        g.add(new Edge(0, 2));
        g.add(new Edge(0, 4));
        g.add(new Edge(0, 7));
        g.add(new Edge(0, 9, 7.3));

        for (Edge edje : g.getAdjacencies(0)) {
            System.out.println(edje.v + " => " + edje.w + " = " + edje.weight);
        }

    }
}
