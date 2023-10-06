import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    private static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<List<Integer>> total = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            total.add(line);
        }

        int range = Math.min(n, m);

        int result = 0;
        for (int i = 1; i <= range; i++) {

            for (int j = 0; j < n - i; j++) {
                for (int k = 0; k < m - i; k++) {
                    int leftTop = total.get(j).get(k);
                    int rightTop = total.get(j).get(k + i);
                    int leftBottom = total.get(j + i).get(k);
                    int rightBottom = total.get(j + i).get(k + i);

                    if (leftTop == rightTop && rightTop == leftBottom && leftBottom == rightBottom) {
                        result = Math.max(result, i);
                    }
                }
            }
        }
        result = (result + 1) * (result + 1);
        System.out.println(result);
    }
}

// 2부터 min(n, m)까지 그냥 전부 탐색.
