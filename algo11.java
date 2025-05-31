import java.util.*;

public class algo11 {

    // Class to represent an edge
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    // Union-Find (Disjoint Set) structure with path compression and union by rank
    static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // Each node is its own parent initially
                rank[i] = 0;    // Rank is 0 for all nodes
            }
        }

        // Find with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        // Union by rank
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;  // Increment rank if both have same rank
                }
            }
        }
    }

    // Kruskal's algorithm to find the MST
    public static void kruskalMST(int numVertices, List<Edge> edges) {
        // Sort edges by weight
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(numVertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            // If u and v are not in the same set, add edge to MST
            if (ds.find(edge.u) != ds.find(edge.v)) {
                mst.add(edge);
                ds.union(edge.u, edge.v);  // Union the sets
            }
        }

        // Print MST edges
        System.out.println("Edge \tWeight");
        for (Edge edge : mst) {
            System.out.println(edge.u + " - " + edge.v + "\t" + edge.weight);
        }
    }

    // Main method to run Kruskal's algorithm
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();

        // Add edges (u, v, weight)
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        // Run Kruskal's algorithm to find MST
        kruskalMST(5, edges);
    }
}
