import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());

        List<Integer> total = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            total.add(i);
        }

        List<List<Integer>> ast = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> xy = Arrays.stream(sc.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            ast.add(xy);
        }
        List<List<Integer>> collect = ast.stream()
                .sorted(Comparator.comparing(i -> i.get(0)))
                .collect(Collectors.toList());

        for (List<Integer> integers : collect) {
            union(integers.get(0), integers.get(1), total);
        }

        int answer = 0;
        int tmp = 0;
        for (int i = 1; i < total.size(); i++) {
            if (total.get(i) != tmp) {
                answer++;
                tmp = total.get(i);
            }
        }

        System.out.println(answer);
    }

    private static int find(int x, List<Integer> total) {
        if (x == total.get(x)) {
            return x;
        }

        return find(total.get(x), total);
    }

    private static void union(int start, int end, List<Integer> total) {
        for (int i = start; i < end; i++) {
            int a = find(i, total);
            int b = find(i + 1, total);
            if (a != b) {
                total.set(b, a);
            }
        }
    }
}
