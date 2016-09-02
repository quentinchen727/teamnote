import java.util.*;

/**
 * Created by qinche on 8/10/2016.
 */
public class SPT {
    private double[] disto;
    private WeightedEdge[] edgeTo;
    PriorityQueue<VertexWithDis> minHeap;

    class VertexWithDis {
        private int v;
        private double dist;

        VertexWithDis(int v, double dist) {
            this.v = v;
            this.dist = dist;
        }

        boolean equals(VertexWithDis o) {
            return this.v == o.v;
        }
    }

    class VertexComparator implements Comparator<VertexWithDis> {

        @Override
        public int compare(VertexWithDis o1, VertexWithDis o2) {
            if ( o1.v < o2.v) return -1;
            else if (o1.v > o2.v) return 1;
            return 0;
        }

    }

    public double distTo(int v) {
        return disto[v];
    }

    public Iterable<WeightedEdge> pathTo(int v) {
        Stack<WeightedEdge> path = new Stack<>();
        for ( WeightedEdge n = edgeTo[v]; n != null; n = edgeTo[n.getFrom()]) {
            path.push(n);
        }
        return path;
    }

    public SPT(int s, Graph g) {
        /*build a SPT from a source vertex and a graph*/
        disto = new double[g.V()];
        edgeTo = new WeightedEdge[g.V];

        PriorityQueue<VertexWithDis> minHeap = new PriorityQueue<>(g.V(), new VertexComparator());

        for (int i = 0; i < g.V(); i++) {
            disto[i] = Double.POSITIVE_INFINITY;
        }

        disto[0] = 0;

        minHeap.add(new VertexWithDis(s, 0));
        while (minHeap.size() != 0) {
            VertexWithDis n = minHeap.poll();
            for (WeightedEdge i : g.adj[n.v]) {
                relax(i);
            }
        }

    }

    private void relax(WeightedEdge e) {
        int from = e.getFrom();
        int to = e.getTo();

        if (disto[to] > disto[from] + e.getWeight()) {
            disto[to] = disto[from] + e.getWeight();
            edgeTo[to] = e;
            VertexWithDis update = new VertexWithDis(to, disto[to]);
            if (minHeap.contains(update)) {
                minHeap.remove(update);
            }
            minHeap.add(update);

        }
    }

    class WeightedEdge {
        private int from, to;
        private double weight;

        WeightedEdge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public double getWeight() {
            return weight;
        }
    }

    class Graph {
        private final int V;
        private Set<WeightedEdge>[] adj;

        /* Adjacent List */
        Graph(int v) {
            V = v;
            adj = (Set<WeightedEdge>[]) new Set[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new HashSet<WeightedEdge>();
            }
        }

        public int V() {
            return V;
        }

        public void addEdge(WeightedEdge e) {
           adj[e.from].add(e);
        }

        public Iterable<WeightedEdge> adj(int v) {
            return adj[v];
        }
    }

}
