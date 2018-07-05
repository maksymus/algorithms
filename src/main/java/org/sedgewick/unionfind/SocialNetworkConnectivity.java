package org.sedgewick.unionfind;

/**
 * <b>Social network connectivity.</b>
 * Given a social network containing N members and a log file containing M timestamps at which times pairs of members formed friendships,
 * design an algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * <p/>
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be MlogN or better and use extra space proportional to N.
 */
public class SocialNetworkConnectivity extends WeightedQuickUnion {

    private int roots;

    public SocialNetworkConnectivity(int n) {
        super(n);
        this.roots = n;
    }

    @Override
    public void union(int p, int q) {
        super.union(p, q);
        roots = roots - 1;
    }

    public boolean allConnected() {
        return roots == 1;
    }

    public static void main(String[] args) {
        SocialNetworkConnectivity uf = new SocialNetworkConnectivity(6);
        uf.union(0, 5);
        uf.union(1, 5);
        uf.union(2, 5);
        uf.union(3, 5);
        //    uf.union(4, 5);

        System.out.println(uf.allConnected());

    }
}