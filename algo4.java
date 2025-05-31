import java.util.*;

public class algo4 {

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

        // DFS recursive method to traverse the graph
        void dfs(int start) {
            boolean[] visited = new boolean[vertices];
            dfsUtil(start, visited);
        }

        // Helper method for the recursive DFS
        void dfsUtil(int node, boolean[] visited) {
            // Mark the current node as visited
            visited[node] = true;
            System.out.print(node + " "); // Print the node

            // Recur for all the adjacent vertices
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    dfsUtil(neighbor, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        // Example for undirected graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);

        // Start DFS traversal from vertex 0
        System.out.println("DFS Traversal starting from node 0:");
        graph.dfs(0);
    }
}
