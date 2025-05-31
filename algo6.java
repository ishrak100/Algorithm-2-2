import java.util.*;

public class algo6 {

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

        // Add a directed edge from v to w
        void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        // Function to perform DFS and generate topological sort
        void topologicalSort() {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            // Perform DFS on all unvisited nodes
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i, visited, stack);
                }
            }

            // Print the topological order
            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }

        // DFS helper function to perform DFS and add nodes to the stack
        void dfs(int node, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;

            // Visit all neighbors of the current node
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, stack);
                }
            }

            // Push the current node to stack after visiting all its neighbors
            stack.push(node);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        // Add directed edges
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Topological Sort of the given graph:");
        graph.topologicalSort();
    }
}
