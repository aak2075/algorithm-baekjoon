import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        List<Long> answer = new ArrayList<>();
        List<List<Long>> dp = new ArrayList<>();
        dp.add(List.of(1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L));

        for (int i = 1; i < 64; i++) {
            List<Long> line = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Long sum = 1L;
                for (int k = 1; k <= j; k++) {
                    sum += dp.get(i - 1).get(k);
                }
                line.add(sum);
            }
            dp.add(line);
        }

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(br.readLine());

            Long sum = dp.get(n - 1).stream()
                    .mapToLong(j -> j)
                    .sum();
            answer.add(sum);
        }

        answer.forEach(System.out::println);
    }
}

// f(n, 0) = f(n - 1, 0)
// f(n, 1) = f(n - 1, 0) + f(n - 1, 1)
// f(n, 9) = f(n - 1, 0) + f(n - 1, 1) + ... + f(n - 1, 9)
