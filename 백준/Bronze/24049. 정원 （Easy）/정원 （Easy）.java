import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<Integer> nInput = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> mInput = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            dp.add(new ArrayList<>(Collections.nCopies(m + 1, -1)));
        }

        for (int i = 1; i < nInput.size() + 1; i++) {
            dp.get(i).set(0, nInput.get(i - 1));
        }
        for (int i = 1; i < mInput.size() + 1; i++) {
            dp.get(0).set(i, mInput.get(i - 1));
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (dp.get(i - 1).get(j).equals(dp.get(i).get(j - 1))) {
                    dp.get(i).set(j, 0);
                } else {
                    dp.get(i).set(j, 1);
                }
            }
        }

        System.out.println(dp.get(n).get(m));
    }

    private static int dp(int n, int m) {


        if (dp(n, m - 1) == dp(n - 1, m)) {
            return 0;
        }
        return 1;
    }
}

// f(n, m) = f(n, m-1) ? f(n-1, m) : 0 : 1
