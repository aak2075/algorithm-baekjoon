import javax.management.remote.JMXServerErrorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final List<Long> memoization = new ArrayList<>(Collections.nCopies(101, -1L));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        memoization.set(1, 1L);
        memoization.set(2, 1L);
        memoization.set(3, 1L);
        memoization.set(4, 2L);
        memoization.set(5, 2L);

        for (int i = 6; i < 101; i++) {
            memoization.set(i, memoization.get(i - 1) + memoization.get(i - 5));
        }

        List<Long> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            answer.add(memoization.get(input));
        }

        answer.forEach(System.out::println);
    }
}
