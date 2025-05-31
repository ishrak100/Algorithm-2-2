import java.util.*;

public class algo7 {

    // Graph class to represent the graph using adjacency list
    static class Graph {
        int vertices;                    // Number of vertices in the graph
        List<Integer>[] adjList;          // Adjacency list for the graph

        // Constructor to initialize the graph with given number of vertices
        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        // Add a directed edge from vertex v to vertex w
        void addEdge(int v, int w) {
            adjList[v].add(w);  // Adds w to the adjacency list of v
        }

        // Step 1: Perform DFS and push vertices to the stack in the order of finishing times
        void dfs(int node, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;  // Mark the node as visited
            // Visit all the neighbors of the current node
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, stack);  // Recursively visit unvisited neighbors
                }
            }
            // After visiting all neighbors of the node, push it to the stack
            stack.push(node);
        }

        // Step 2: Reverse the graph (transpose the edges)
        // This creates a new graph where all edges are reversed
        Graph reverseGraph() {
            Graph reversed = new Graph(vertices);  // New graph for the reversed edges
            // Traverse through all the edges and reverse them
            for (int v = 0; v < vertices; v++) {
                for (int neighbor : adjList[v]) {
                    reversed.addEdge(neighbor, v);  // Reverse the edge (v -> neighbor) to (neighbor -> v)
                }
            }
            return reversed;
        }

        // Step 3: Perform DFS on the reversed graph in the order of vertices in the stack
        // This step will discover all strongly connected components (SCCs)
        void dfsOnReversedGraph(int node, boolean[] visited, List<Integer> scc) 
        {
            visited[node] = true;  // Mark the node as visited
            scc.add(node);         // Add the node to the current SCC
            // Visit all the neighbors of the node in the reversed graph
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    dfsOnReversedGraph(neighbor, visited, scc);  // Recursively visit neighbors
                }
            }
        }

        // Kosaraju's algorithm to find all SCCs in the graph and the number of SCCs
        void kosarajuSCC() {
            Stack<Integer> stack = new Stack<>();  // Stack to store vertices in finishing order
            boolean[] visited = new boolean[vertices];  // To keep track of visited vertices

            // Step 1: Perform DFS and push vertices onto the stack by their finishing times
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i, visited, stack);  // Start DFS if the vertex is unvisited
                }
            }

            // Step 2: Reverse the graph (transpose all edges)
            Graph reversed = reverseGraph();

            // Step 3: Perform DFS on the reversed graph using the vertices in finishing time order
            Arrays.fill(visited, false);  // Reset visited array for the second DFS
            int sccCount = 0;  // Initialize SCC count

            // Process each vertex in the order of their finishing times (popped from the stack)
            while (!stack.isEmpty()) {
                int node = stack.pop();
                // If the vertex hasn't been visited in the reversed graph, it's part of an SCC
                if (!visited[node]) {
                    List<Integer> scc = new ArrayList<>();  // List to hold nodes of the current SCC
                    reversed.dfsOnReversedGraph(node, visited, scc);  // Find all nodes in the current SCC
                    System.out.println("SCC: " + scc);  // Print the current SCC
                    sccCount++;  // Increment the SCC count
                }
            }

            // Output the number of SCCs
            System.out.println("Number of Strongly Connected Components: " + sccCount);
        }
    }

    public static void main(String[] args) {
        // Create the graph with 5 vertices
        Graph graph = new Graph(5);
        
        // Add directed edges to the graph (example graph)
        graph.addEdge(0, 2);  // Edge from 0 -> 2
        graph.addEdge(2, 1);  // Edge from 2 -> 1
        graph.addEdge(1, 0);  // Edge from 1 -> 0 (forming a cycle 0 -> 2 -> 1 -> 0)
        graph.addEdge(1, 3);  // Edge from 1 -> 3
        graph.addEdge(3, 4);  // Edge from 3 -> 4

        // Find and print all strongly connected components (SCCs)
        System.out.println("Strongly Connected Components:");
        graph.kosarajuSCC();
    }
}
