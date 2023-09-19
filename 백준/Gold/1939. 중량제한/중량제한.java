import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {

    private static final List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);


        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int max = 0;
        int min = 987654321;
        for (int i = 0; i < m; i++) {
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            Node toTarget = new Node(input.get(1), input.get(2));
            Node fromTarget = new Node(input.get(0), input.get(2));
            graph.get(input.get(0)).add(toTarget);
            graph.get(input.get(1)).add(fromTarget);

            max = Math.max(max, input.get(2));
            min = Math.min(min ,input.get(2));
        }
        String[] split2 = br.readLine().split(" ");
        int start = Integer.parseInt(split2[0]);
        int end = Integer.parseInt(split2[1]);

        int result = search(min, max, start, end);
        System.out.println(result);
    }

    private static int search(int startWeight, int endWeight, int start, int end) {
        int result = 0;

        while (startWeight <= endWeight) {
            int mid = (endWeight + startWeight) / 2;
            if (bfs(mid, start, end)) {
                startWeight = mid + 1;
                result = mid;
            } else {
                endWeight = mid - 1;
            }
        }
        return result;
    }

    private static boolean bfs(int weight, int start, int end) {
        Deque<Integer> bfs = new ArrayDeque<>();
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(graph.size(), false));

        visited.set(start, true);
        bfs.offer(start);
        while (!bfs.isEmpty()) {
            int poll = bfs.poll();
            if (poll == end) {
                return true;
            }
            List<Node> nodes = graph.get(poll);

            for (Node node : nodes) {
                if (!visited.get(node.n) && node.v >= weight) {
                    visited.set(node.n, true);
                    bfs.offer(node.n);
                }
            }
        }

        return false;
    }

    static class Node {
        int n;
        int v;

        public Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
}
