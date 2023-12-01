import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final List<List<List<Integer>>> table = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 101; i++) {
            List<List<Integer>> line = new ArrayList<>();
            for (int j = 0; j < 101; j++) {
                List<Integer> list = new ArrayList<>(Collections.nCopies(101, Integer.MAX_VALUE));
                line.add(list);
            }
            table.add(line);
        }

        while (true) {
            String input = br.readLine();

            String[] split = input.split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);

            if (a == -1 && b == -1 && c == -1) {
                return;
            }

            int result = dp(a, b, c);

            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + result);
        }
    }

    private static int dp(int a, int b, int c) {
        int aIndex = a + 50;
        int bIndex = b + 50;
        int cIndex = c + 50;

        if (!table.get(aIndex).get(bIndex).get(cIndex).equals(Integer.MAX_VALUE)) {
            return table.get(aIndex).get(bIndex).get(cIndex);
        }

        if (a <= 0 || b <= 0 || c <= 0) {
            table.get(aIndex).get(bIndex).set(cIndex, 1);
            return 1;
        }

        if (a > 20 || b > 20 || c > 20) {
            int result = dp(20, 20, 20);
            table.get(aIndex).get(bIndex).set(cIndex, result);
            return result;
        }

        if (a < b && b < c) {
            int result = dp(a, b, c - 1) + dp(a, b - 1, c - 1) - dp(a, b - 1, c);
            table.get(aIndex).get(bIndex).set(cIndex, result);
            return result;
        }

        int result = dp(a - 1, b, c) + dp(a - 1, b - 1, c) + dp(a - 1, b, c - 1) - dp(a - 1, b - 1, c - 1);
        table.get(aIndex).get(bIndex).set(cIndex, result);
        return result;
    }
}
