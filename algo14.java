import java.util.*;

public class algo14 {

    // Class to represent an edge
    static class Edge {
        int v, weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    // Method to implement Floyd-Warshall algorithm using adjacency list
    public static void floydWarshall(int numVertices, List<List<Edge>> graph) {
        // Create a distance matrix to store the shortest path distances
        int[][] dist = new int[numVertices][numVertices];

        // Initialize the distance matrix with the given graph distances
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;  // Distance from a vertex to itself is 0
                } else {
                    dist[i][j] = Integer.MAX_VALUE;  // No direct path initially
                }
            }
        }

        // Add the edges from the adjacency list to the distance matrix
        for (int u = 0; u < numVertices; u++) {
            for (Edge edge : graph.get(u)) {
                int v = edge.v;
                int weight = edge.weight;
                dist[u][v] = weight;  // Set the direct distance between u and v
            }
        }

        // Apply the Floyd-Warshall algorithm to find all pairs shortest paths
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    // If a shorter path through vertex k is found, update the distance
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distances between every pair of vertices
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF\t");  // Print "INF" if no path exists
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        int numVertices = 4;

        // Create a graph using an adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (u, v, weight) to the graph
        graph.get(0).add(new Edge(1, 3));
        graph.get(0).add(new Edge(3, 7));
        graph.get(1).add(new Edge(0, 8));
        graph.get(1).add(new Edge(2, 2));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(3, 1));
        graph.get(3).add(new Edge(1, 4));
        graph.get(3).add(new Edge(2, 6));

        // Run Floyd-Warshall algorithm
        floydWarshall(numVertices, graph);
    }
}
