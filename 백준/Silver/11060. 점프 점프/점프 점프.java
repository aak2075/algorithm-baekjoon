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
        List<Integer> miro = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if (n == 1) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs(miro));
    }

    private static int bfs(List<Integer> miro) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> visited = new ArrayList<>(Collections.nCopies(1001, -1));

        queue.add(0);
        visited.set(0, 0);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int jump = miro.get(poll);

            if (jump == 0) {
                continue;
            }
            for (int i = 1; i <= jump; i++) {
                if (poll + i > miro.size() - 1 || visited.get(poll + i) >= 0) {
                    continue;
                }
                queue.add(poll + i);
                visited.set(poll + i, visited.get(poll) + 1);
            }
        }

        return visited.get(miro.size() - 1);
    }
}
