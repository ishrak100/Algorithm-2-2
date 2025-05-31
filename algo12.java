import java.util.*;

public class algo12 {

    // Class to represent an edge in the graph
    static class Edge {
        int v, weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    // Method to implement Dijkstra's Algorithm
    public static void dijkstra(int numVertices, List<List<Edge>> graph, int source) {
        // Array to store the shortest distance from the source to each vertex
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[source] = 0; // Distance to the source is 0

        // Min-heap (priority queue) to select the vertex with the smallest distance
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(source, 0)); // Add the source vertex with distance 0

        while (!pq.isEmpty()) {
            // Get the vertex with the smallest distance
            Edge current = pq.poll();
            int u = current.v;

            // Process all adjacent vertices of the current vertex
            for (Edge edge : graph.get(u)) {
                int v = edge.v;
                int weight = edge.weight;

                // If the distance to the adjacent vertex can be shortened, update it
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v])); // Add the updated vertex to the priority queue
                }
            }
        }

        // Print the shortest distance to each vertex from the source
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(i + " \t " + dist[i]);
        }
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        int numVertices = 5;

        // Create a graph using an adjacency list representation
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (u, v, weight) to the graph
        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(3, 6));
        graph.get(1).add(new Edge(0, 2));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 8));
        graph.get(1).add(new Edge(4, 5));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(4, 7));
        graph.get(3).add(new Edge(0, 6));
        graph.get(3).add(new Edge(1, 8));
        graph.get(3).add(new Edge(4, 9));
        graph.get(4).add(new Edge(1, 5));
        graph.get(4).add(new Edge(2, 7));
        graph.get(4).add(new Edge(3, 9));

        // Run Dijkstra's algorithm from the source vertex 0
        dijkstra(numVertices, graph, 0);
    }
}
