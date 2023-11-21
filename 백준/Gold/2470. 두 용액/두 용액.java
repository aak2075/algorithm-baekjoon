import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = numbers.size() - 1;
        int minLeft = numbers.get(0);
        int minRight = numbers.get(right);

        while(left < right) {
            int now = numbers.get(left) + numbers.get(right);
            if (Math.abs(now) < Math.abs(min)) {
                min = now;
                minLeft = numbers.get(left);
                minRight = numbers.get(right);
            }

            if (now > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(minLeft + " " + minRight);
    }
}

