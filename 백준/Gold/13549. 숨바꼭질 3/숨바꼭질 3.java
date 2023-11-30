import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        Queue<Node> queue = new PriorityQueue<>();
        List<Integer> dist = new ArrayList<>(Collections.nCopies(100001, Integer.MAX_VALUE));
        queue.offer(new Node(n, 0));
        dist.set(n, 0);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (poll.w > dist.get(poll.v)) {
                continue;
            }

            List<Node> adjNodes = new ArrayList<>();

            adjNodes.add(new Node(poll.v - 1, poll.w + 1));
            adjNodes.add(new Node(poll.v + 1, poll.w + 1));
            adjNodes.add(new Node(poll.v * 2, poll.w));

            for (Node adjNode : adjNodes) {
                if (adjNode.v < 0 || adjNode.v > 100000) {
                    continue;
                }

                int nextDist = adjNode.w;
                if (nextDist < dist.get(adjNode.v)) {
                    dist.set(adjNode.v, nextDist);
                    queue.offer(new Node(adjNode.v, nextDist));
                }
            }
        }

        System.out.println(dist.get(k));
    }

    static class Node implements Comparable<Node> {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return v - o.v;
        }
    }
}
