import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        List<List<Boolean>> graph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            graph.add(new ArrayList<>(Collections.nCopies(n, true)));
        }

        for (int i = 0; i < k; i++) {
            String[] line = scanner.nextLine().split(" ");
            int fromX = Integer.parseInt(line[0]);
            int toX = Integer.parseInt(line[2]);
            int fromY = Integer.parseInt(line[1]);
            int toY = Integer.parseInt(line[3]);

            for (int j = fromY; j < toY; j++) {
                for (int q = fromX; q < toX; q++) {
                    graph.get(j).set(q, false);
                }
            }
        }
        List<Integer> widths = bfs(graph, m, n);
        Collections.sort(widths);
        System.out.println(widths.size());
        for (Integer width : widths) {
            System.out.print(width + " ");
        }
    }

    private static List<Integer> bfs(List<List<Boolean>> graph, int m, int n) {
        List<Integer> dx = List.of(-1, 0, 1, 0);
        List<Integer> dy = List.of(0, 1, 0, -1);

        Deque<List<Integer>> queue = new ArrayDeque<>();

        List<Integer> widths = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(0).size(); j++) {
                if (!graph.get(i).get(j)) {
                    continue;
                }
                int width = 0;
                queue.add(List.of(i, j));
                while (!queue.isEmpty()) {
                    List<Integer> poll = queue.poll();
                    graph.get(poll.get(0)).set(poll.get(1), false);
                    width++;
                    for (int k = 0; k < dx.size(); k++) {
                        int y = poll.get(0) + dy.get(k);
                        int x = poll.get(1) + dx.get(k);
                        if (x < 0 || x >= n || y < 0 || y >= m) {
                            continue;
                        }
                        if (!graph.get(y).get(x)) {
                            continue;
                        }
                        graph.get(y).set(x, false);
                        queue.add(List.of(y, x));
                    }
                }
                if (width > 0) {
                    widths.add(width);
                }
            }
        }
        return widths;
    }
}
