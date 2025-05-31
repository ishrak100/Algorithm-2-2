import java.util.*;


public class algo1 {
    private int V; // Number of vertices
    private ArrayList<Integer>[] adjList; // Adjacency list to store graph

    // Constructor to initialize graph
     algo1(int V) {
        this.V = V;
        adjList = new ArrayList[V]; // Initialize the adjacency list
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>(); // Initialize each list in the adjacency list
        }
    }

    // Method to add an edge to the graph
     void addEdge(int u, int v) {
        adjList[u].add(v); // Add v to u's list
        adjList[v].add(u); // Add u to v's list (undirected graph)
    }

    // BFS method to print the traversal starting from a source node
     void bfs(int start) {
        boolean[] visited = new boolean[V]; // To track visited nodes
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS

        visited[start] = true; // Mark the start node as visited
        queue.add(start); // Add start node to the queue

        while (!queue.isEmpty()) {
            int node = queue.remove(); // Remove the node from the queue
            System.out.print(node + " "); // Print the node

            // Add unvisited neighbors to the queue
            for (int i = 0; i < adjList[node].size(); i++) {
                int neighbor = adjList[node].get(i);
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // Mark as visited
                    queue.add(neighbor); // Add neighbor to the queue
                }
            }

        }
    }

    public static void main(String[] args) {
        algo1 graph = new algo1(5); // Create a graph with 5 vertices

        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        System.out.println("Breadth-First Search (BFS) starting from vertex 0:");
        graph.bfs(0); // Start BFS from vertex 0
    }
}
