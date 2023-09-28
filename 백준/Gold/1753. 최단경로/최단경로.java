import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int v = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);
        int start = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        while (br.ready()) {
            List<Integer> edge = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            graph.get(edge.get(0)).add(new Node(edge.get(1), edge.get(2)));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        List<Integer> dist = new ArrayList<>(Collections.nCopies(v + 1, Integer.MAX_VALUE));
        dist.set(start, 0);

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (poll.cost > dist.get(poll.v)) {
                continue;
            }
            List<Node> nodes = graph.get(poll.v);
            for (Node node : nodes) {
                int nextDist = poll.cost + node.cost;
                if (nextDist < dist.get(node.v)) {
                    dist.set(node.v, nextDist);
                    pq.offer(new Node(node.v, nextDist));
                }
            }
        }
        for (int i = 1; i < dist.size(); i++) {
            if (dist.get(i).equals(Integer.MAX_VALUE)) {
                System.out.println("INF");
            } else {
                System.out.println(dist.get(i));
            }
        }
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
