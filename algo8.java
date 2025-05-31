import java.util.*;

public class algo8 {

    static class Graph {
        private int numNodes; // Number of nodes (vertices)
        private List<Integer>[] adjacencyList; // Adjacency List

        // Constructor
        public Graph(int numNodes) {
            this.numNodes = numNodes;
            adjacencyList = new ArrayList[numNodes];
            for (int i = 0; i < numNodes; i++) {
                adjacencyList[i] = new ArrayList<>();
            }
        }

        // Add an edge between two nodes
        public void addEdge(int node, int neighbor) {
            adjacencyList[node].add(neighbor);
            adjacencyList[neighbor].add(node);
        }

        // Helper variables for DFS
        private int time = 0;
        private int[] discoveryTime, lowTime, parent;
        private boolean[] visited;

        // Method to find and print bridges
        public void findBridges() {
            discoveryTime = new int[numNodes];
            lowTime = new int[numNodes];
            parent = new int[numNodes];
            visited = new boolean[numNodes];

            Arrays.fill(parent, -1); // Initialize parent as -1

            // Start DFS for every unvisited node
            for (int i = 0; i < numNodes; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }

        // DFS utility function to find Bridges
        private void dfs(int node) {
            visited[node] = true;
            discoveryTime[node] = lowTime[node] = ++time; // Set discovery time and low value

            // Explore all neighbors of the current node
            for (int neighbor : adjacencyList[node]) {
                if (!visited[neighbor]) {
                    parent[neighbor] = node; // Set parent of neighbor

                    // Recur for DFS
                    dfs(neighbor);

                    // Update lowTime[node] for back edges
                    lowTime[node] = Math.min(lowTime[node], lowTime[neighbor]);

                    // If lowTime[neighbor] > discoveryTime[node], then (node, neighbor) is a bridge
                    if (lowTime[neighbor] > discoveryTime[node]) {
                        System.out.println("Bridge: " + node + " - " + neighbor);
                    }
                } else if (neighbor != parent[node]) {
                    // Update lowTime[node] for back edge
                    lowTime[node] = Math.min(lowTime[node], discoveryTime[neighbor]);
                }
            }
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        Graph graph = new Graph(5); // Graph with 5 nodes
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        graph.findBridges();
    }
}