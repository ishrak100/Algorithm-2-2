import java.util.*;

public class algo5 {

    static class Graph {
        int vertices;
        ArrayList<Integer>[] adjList;

        // Constructor to initialize the graph
        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        // Add an undirected edge between v and w
        void addEdge(int v, int w) {
            adjList[v].add(w);
            adjList[w].add(v); // For undirected graph, add edge in both directions
        }

        // Function to detect cycle in the graph using DFS
        boolean hasCycle() {
            boolean[] visited = new boolean[vertices];

            // Run DFS from each unvisited vertex to cover disconnected graphs
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    if (dfs(i, visited, -1)) {
                        return true; // Cycle detected
                    }
                }
            }
            return false; // No cycle found
        }

        // DFS to detect cycle
        boolean dfs(int node, boolean[] visited, int parent) {
            visited[node] = true;

            // Check all neighbors
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    if (dfs(neighbor, visited, node)) {
                        return true;
                    }
                } else if (neighbor != parent) { // Cycle detected
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        // Example for undirected graph with a cycle: 0 -> 1 -> 2 -> 0
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        // Check if the graph has a cycle
        if (graph.hasCycle()) {
            System.out.println("Graph contains a cycle");
        } else {
            System.out.println("Graph does not contain a cycle");
        }
    }
}
