import java.util.*;

public class algo3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read number of cities, roads, fuel, and starting city
        int C = sc.nextInt();  // Cities
        int R = sc.nextInt();  // Roads
        int K = sc.nextInt();  // Fuel
        int L = sc.nextInt();  // Starting city

        // Initialize adjacency list for the graph
        List<Integer>[] adjList = new ArrayList[C + 1];
        for (int i = 1; i <= C; i++) {
            adjList[i] = new ArrayList<>();
        }

        // Read roads and populate adjacency list
        for (int i = 0; i < R; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjList[u].add(v);
            adjList[v].add(u);  // Bidirectional road
        }

        // Call BFS to find the number of cities Sharara can visit
        System.out.println(bfs(C, adjList, L, K));
    }

    public static int bfs(int C, List<Integer>[] adjList, int L, int K) {
        boolean[] visited = new boolean[C + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{L, 0});  // [city, distance]
        visited[L] = true;

        int reachableCities = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int city = current[0], dist = current[1];

            // If distance exceeds fuel limit, stop further exploration
            if (dist > K) continue;

            reachableCities++;  // Count this city as reachable

            // Visit unvisited neighbors
            for (int neighbor : adjList[city]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, dist + 1});
                }
            }
        }

        return reachableCities;
    }
}
