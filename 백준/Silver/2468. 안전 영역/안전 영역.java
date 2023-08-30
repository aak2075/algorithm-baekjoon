import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> line = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            matrix.add(line);
        }

        Integer max = matrix.stream()
                .flatMap(List::stream)
                .max(Integer::compareTo)
                .orElse(0);

        int mmax = 0;
        for (int i = 0; i < max; i++) {
            mmax = Math.max(mmax, rain(matrix, i));
        }
        System.out.println(mmax);
    }

    private static int rain(List<List<Integer>> matrix, int height) {
        List<List<Boolean>> flooded = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            flooded.add(new ArrayList<>(Collections.nCopies(matrix.get(0).size(), false)));
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (matrix.get(i).get(j) <= height) {
                    flooded.get(i).set(j, true);
                }
            }
        }

       return bfs(flooded);
    }

    private static int bfs(List<List<Boolean>> flooded) {
        List<Integer> dx = List.of(-1, 0, 1, 0);
        List<Integer> dy = List.of(0, 1, 0, -1);

        int result = 0;
        for (int i = 0; i < flooded.size(); i++) {
            for (int j = 0; j < flooded.get(0).size(); j++) {
                if (flooded.get(i).get(j)) {
                    continue;
                }
                result++;
                Deque<List<Integer>> queue = new ArrayDeque<>();
                queue.add(List.of(i, j));
                while(!queue.isEmpty()) {
                    List<Integer> poll = queue.poll();
                    for (int k = 0; k < dx.size(); k++) {
                        int y = poll.get(0) + dy.get(k);
                        int x = poll.get(1) + dx.get(k);
                        if (y < 0 || y >= flooded.size() || x < 0 || x >= flooded.get(0).size()) {
                            continue;
                        }
                        if (!flooded.get(y).get(x)) {
                            queue.add(List.of(y, x));
                            flooded.get(y).set(x, true);
                        }
                    }
                }
            }
        }
        return result;
    }
}
