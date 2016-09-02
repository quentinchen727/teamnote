import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by qinche on 8/12/2016.
 */
public class TopologicalSort {
    /* Topological sort is only possible in DAG, i.e, directed acyclic graph */
    LinkedList<Integer> order;
    private Graph g;

    public TopologicalSort(Graph g) {
        this.g = g;
        order = new LinkedList<>();

        boolean[] visited = new boolean[g.V()];

        for(int i = 0; i < g.V(); i++) {
            topologicalSort(i, visited);
        }
    }

    private void topologicalSort(int i, boolean[] visited) {
        visited[i] = true;
        for (int j : g.adj(i)) {
            if (visited[j] != true) {
                topologicalSort(j, visited);
            }
        }
        order.push(i);
    }

    public Iterable<Integer> order() {
        return order;
    }
}

class Graph {
    private int v;
    private LinkedList<Integer>[] adj;
    Graph(int v) {
        adj =(LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public int V() {
        return v;
    }

    public LinkedList<Integer> adj(int i) {
        return adj[i];
    }
}
