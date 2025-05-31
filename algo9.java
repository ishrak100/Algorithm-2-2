import java.util.*;

public class algo9 {

    static class SimpleGraph{
    private int numNodes;  // Number of nodes (vertices)
    private List<Integer>[] adjList; // Adjacency List to store the graph

    // Constructor to initialize the graph
    public SimpleGraph(int nodes) {
        numNodes = nodes;
        adjList = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    // Method to add an edge between two nodes
    public void addEdge(int node, int neighbor) {
        adjList[node].add(neighbor);  // Connect node to neighbor
        adjList[neighbor].add(node);  // Connect neighbor to node (undirected graph)
    }

    // Helper arrays for DFS
    private int time = 0;
    private int[] discoveryTime, lowTime, parent;
    private boolean[] visited;
    private boolean[] articulationPoints;

    // Method to find and print all articulation points
    public void findArticulationPoints() {
        discoveryTime = new int[numNodes];
        lowTime = new int[numNodes];
        parent = new int[numNodes];
        visited = new boolean[numNodes];
        articulationPoints = new boolean[numNodes];

        Arrays.fill(parent, -1);  // Set parent of all nodes to -1

        // Start DFS for every unvisited node
        for (int i = 0; i < numNodes; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        // Print the articulation points
        for (int i = 0; i < numNodes; i++) {
            if (articulationPoints[i]) {
                System.out.println("Articulation Point: " + i);
            }
        }
    }

    // DFS function to find articulation points
    private void dfs(int node) {
        visited[node] = true;
        discoveryTime[node] = lowTime[node] = ++time;

        int children = 0;

        // Explore all neighbors of the current node
        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                parent[neighbor] = node;
                children++;

                // Recursively visit the neighbor
                dfs(neighbor);

                // Update lowTime[node] for back edges
                lowTime[node] = Math.min(lowTime[node], lowTime[neighbor]);

                // Check if current node is an articulation point
                if (parent[node] == -1 && children > 1) {
                    articulationPoints[node] = true;  // Root node with multiple children
                }
                if (parent[node] != -1 && lowTime[neighbor] >= discoveryTime[node]) {
                    articulationPoints[node] = true;  // Non-root node with a valid child
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
        SimpleGraph graph = new SimpleGraph(5);  // Create a graph with 5 nodes
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        graph.findArticulationPoints();  // Find and print all articulation points
    }
}

