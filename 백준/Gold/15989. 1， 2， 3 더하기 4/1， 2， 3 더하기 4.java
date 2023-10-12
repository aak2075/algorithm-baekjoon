import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();
        int max = -1;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            numbers.add(number);
            max = Math.max(max, number);
        }

        List<List<Integer>> dp = new ArrayList<>();
        dp.add(List.of(0, 0, 0, 0));
        dp.add(List.of(0, 1, 0, 0));
        dp.add(List.of(0, 1, 1, 0));
        dp.add(List.of(0, 1, 1, 1));
        dp.add(List.of(0, 1, 2, 1));

        for (int i = 5; i <= max; i++) {
            int dp1 = dp.get(i - 1).get(1);
            int dp2 = dp.get(i - 2).get(1) + dp.get(i - 2).get(2);
            int dp3 = dp.get(i - 3).get(1) + dp.get(i - 3).get(2) + dp.get(i - 3).get(3);
            dp.add(List.of(0, dp1, dp2, dp3));
        }

        for (Integer number : numbers) {
            int sum = dp.get(number).stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println(sum);
        }
    }
}

// 1, 2, 3의 합으로 n을 만드는 경우의 수

// f[i][1] = f[i-1][1]
// f[i][2] = f[i-2][1] + f[i-2][2]
// f[i][3] = f[i-3][1] + f[i-3][2] + f[i-3][3]



