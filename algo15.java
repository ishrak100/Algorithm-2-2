import java.util.*;

public class algo15 {

    static class Graph {
        int V;
        List<List<Integer>> adj;
        int time;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            time = 0;
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v); // Directed edge
        }

        void dfs(int u, boolean[] visited, int[] discoveryTime, int[] finishTime, int[] parent) {
            visited[u] = true;
            discoveryTime[u] = ++time;

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    System.out.println("Tree Edge: " + u + " -> " + v);
                    parent[v] = u;
                    dfs(v, visited, discoveryTime, finishTime, parent);
                } else {
                    // Already visited, classify the edge
                    if (finishTime[v] == 0) {
                        System.out.println("Back Edge: " + u + " -> " + v);  // v is ancestor of u
                    } else if (discoveryTime[u] < discoveryTime[v]) {
                        System.out.println("Forward Edge: " + u + " -> " + v); // v is descendant, already finished
                    } else {
                        System.out.println("Cross Edge: " + u + " -> " + v); // v is on a different DFS branch
                    }
                }
            }

            finishTime[u] = ++time;
        }

        void classifyEdges() {
            boolean[] visited = new boolean[V];
            int[] discoveryTime = new int[V];
            int[] finishTime = new int[V];
            int[] parent = new int[V];
            Arrays.fill(parent, -1);

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(i, visited, discoveryTime, finishTime, parent);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 2);  // Back Edge to 2

        g.classifyEdges();
    }
}
