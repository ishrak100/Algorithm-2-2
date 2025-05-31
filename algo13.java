import java.util.*;

public class algo13 {

    // Class to represent an edge
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    // Method to implement Bellman-Ford algorithm
    public static void bellmanFord(int numVertices, List<Edge> edges, int source) {
        // Initialize distances from the source to all vertices
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE); // Set all distances to infinity
        dist[source] = 0; // Distance to the source is 0

        // Relax all edges (numVertices - 1) times
        for (int i = 1; i < numVertices; i++) {
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int weight = edge.weight;

                // If the path through u to v is shorter, update dist[v]
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            int weight = edge.weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print the shortest distances
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < numVertices; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(i + " \t Infinity"); // If the vertex is unreachable
            } else {
                System.out.println(i + " \t " + dist[i]);
            }
        }
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        int numVertices = 5;

        // Create a graph using an edge list (u, v, weight)
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        // Run Bellman-Ford algorithm from the source vertex 0
        bellmanFord(numVertices, edges, 0);
    }
}
