import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final List<List<Node>> graph = new ArrayList<>();
    private static int INF = 200000 * 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            String[] edge = br.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);

            List<Node> fromAdjNodes = graph.get(from);
            List<Node> toAdjNodes = graph.get(to);
            fromAdjNodes.add(new Node(to, weight));
            toAdjNodes.add(new Node(from, weight));
        }

        String[] vertexes = br.readLine().split(" ");
        int v1 = Integer.parseInt(vertexes[0]);
        int v2 = Integer.parseInt(vertexes[1]);

        int from1ToV1 = dijkstra(1, v1);
        int fromV1ToV2 = dijkstra(v1, v2);
        int fromV2ToN = dijkstra(v2, n);
        int from1ToV2 = dijkstra(1, v2);
        int fromV2ToV1 = dijkstra(v2, v1);
        int fromV1ToN = dijkstra(v1, n);

        int answer1 = from1ToV1 + fromV1ToV2 + fromV2ToN;
        int answer2 = from1ToV2 + fromV2ToV1 + fromV1ToN;

        if (answer1 >= INF && answer2 >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(answer1, answer2));
        }
    }

    private static int dijkstra(int from, int to) {
        Queue<Node> queue = new PriorityQueue<>();
        List<Integer> dist = new ArrayList<>(Collections.nCopies(graph.size(), INF));
        dist.set(from, 0);
        queue.offer(new Node(from, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (poll.cost > dist.get(poll.v)) {
                continue;
            }

            List<Node> nodes = graph.get(poll.v);
            for (Node node : nodes) {
                int nextDist = poll.cost + node.cost;
                if (nextDist < dist.get(node.v)) {
                    dist.set(node.v, nextDist);
                    queue.offer(new Node(node.v, nextDist));
                }
            }
        }

        return dist.get(to);
    }

    static class Node implements Comparable<Node> {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}
