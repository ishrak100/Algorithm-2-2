import java.util.*;

public class algo10 {

    // Class to represent an edge to a destination node with a certain weight
    static class Edge {
        int dest, weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Method to perform Prim's algorithm and print the Minimum Spanning Tree (MST)
    public static void primMST(ArrayList<ArrayList<Edge>> graph) {
        int n = graph.size();

        int[] key = new int[n]; // Stores the minimum weight edge to include vertex in MST
        boolean[] inMST = new boolean[n]; // Marks if vertex is included in MST
        int[] parent = new int[n]; // Stores parent of each vertex in MST

        Arrays.fill(key, Integer.MAX_VALUE); // Initialize all keys as infinity
        key[0] = 0; // Start from vertex 0
        parent[0] = -1; // Root node has no parent

        // Repeat to include all vertices in MST
        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            int min = Integer.MAX_VALUE;

            // Pick the vertex not in MST with the smallest key value
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && key[i] < min) {
                    min = key[i];
                    u = i;
                }
            }

            inMST[u] = true; // Include vertex u in MST

            // Update key and parent for all neighbors of u
            for (int i = 0; i < graph.get(u).size(); i++) {
                Edge e = graph.get(u).get(i);
                if (!inMST[e.dest] && e.weight < key[e.dest]) {
                    key[e.dest] = e.weight;
                    parent[e.dest] = u;
                }
            }

        }

        // Print the edges of the MST along with their weights
        System.out.println("Edge \tWeight");
        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }

    // Adds an undirected edge between u and v with weight w
    public static void addEdge(ArrayList<ArrayList<Edge>> graph, int u, int v, int w) {
        graph.get(u).add(new Edge(v, w)); // Add edge u->v
        graph.get(v).add(new Edge(u, w)); // Add edge v->u (undirected)
    }

    public static void main(String[] args) {
        int n = 5; // Number of vertices

        // Initialize graph as adjacency list
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        // Add edges with weights
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 3, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 2, 4, 7);
        addEdge(graph, 3, 4, 9);

        // Run Prim's MST algorithm
        primMST(graph);
    }
}
