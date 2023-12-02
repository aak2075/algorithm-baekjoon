import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final List<Integer> memoization = new ArrayList<>(Collections.nCopies(1000001, -1));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        memoization.set(0, 0);
        memoization.set(1, 1);
        memoization.set(2, 2);
        memoization.set(3, 3);
        memoization.set(4, 5);

        for (int i = 5; i <= n; i++) {
            memoization.set(i, (memoization.get(i - 1) + memoization.get(i - 2)) % 15746);
        }

        System.out.println(memoization.get(n));
    }
}
