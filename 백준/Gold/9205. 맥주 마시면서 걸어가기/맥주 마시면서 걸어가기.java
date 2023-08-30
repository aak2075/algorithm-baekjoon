import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(scanner.nextLine());
            List<List<Integer>> vertexes = new ArrayList<>();
            List<Boolean> visited = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                vertexes.add(inputIntegers(scanner));
                visited.add(false);
            }
            Map<Integer, List<Integer>> graph = draw(vertexes);
            if (bfs(graph, visited)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    static List<Integer> inputIntegers(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private static Map<Integer, List<Integer>> draw(List<List<Integer>> vertexes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < vertexes.size(); i++) {
            for (int j = 0; j < vertexes.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (diff(vertexes.get(i), vertexes.get(j)) <= 1000) {
                    if (!graph.containsKey(i)) {
                        List<Integer> values = new ArrayList<>();
                        values.add(j);
                        graph.put(i, values);
                    } else {
                        List<Integer> values = graph.get(i);
                        values.add(j);
                    }
                } else {
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<>());
                    }
                }
            }
        }
        return graph;
    }

    private static boolean bfs(Map<Integer, List<Integer>> graph, List<Boolean> visited) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> values = graph.get(poll);
            for (Integer value : values) {
                if (!visited.get(value)) {
                    queue.add(value);
                    visited.set(value, true);
                }
            }
        }
        return visited.get(graph.size() - 1);
    }

    static int diff(List<Integer> a, List<Integer> b) {
        return Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1));
    }
}
