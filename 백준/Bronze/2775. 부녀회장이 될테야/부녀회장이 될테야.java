import java.util.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCase; i++) {
            int k = Integer.parseInt(sc.nextLine());
            int n = Integer.parseInt(sc.nextLine());

            List<List<Integer>> answer = new ArrayList<>();
            List<Integer> first = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                first.add(j);
            }
            answer.add(first);

            // 각 층
            for (int j = 1; j <= k; j++) {
                List<Integer> line = new ArrayList<>();
                List<Integer> before = answer.get(j - 1);
                for (int q = 1; q <= n; q++) {
                    int sum = IntStream.range(0, q)
                            .map(before::get)
                            .sum();
                    line.add(sum);
                }
                answer.add(line);
            }

            System.out.println(answer.get(k).get(n - 1));
        }
    }
}
