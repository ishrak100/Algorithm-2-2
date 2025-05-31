import java.util.*;

public class algo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int E = sc.nextInt();

        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            adjList[A].add(B);
        }

        int X = sc.nextInt();

        System.out.println(adjList[X].size());

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        dist[X] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < adjList[node].size(); i++) {
                int neighbor = adjList[node].get(i);
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(dist[i]);
            if (i < N) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

}
