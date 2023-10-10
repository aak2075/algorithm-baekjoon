import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    static List<Integer> input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int result = sum(0, i) + sum(i, j) + sum(j, k) + sum(k, n);
                    max = Math.max(max, result);
                }
            }
        }
        System.out.println(max);
    }

    private static int sum(int x, int y) {
        int result = 1;
        for (int i = x; i < y; i++) {
            result *= input.get(i);
        }
        return result;
    }
}
