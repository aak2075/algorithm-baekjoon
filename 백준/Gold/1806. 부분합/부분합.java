import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] splited = br.readLine().split(" ");
        int n = Integer.parseInt(splited[0]);
        int s = Integer.parseInt(splited[1]);

        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int left = 0;
        int right = 0;
        int sum = numbers.get(left);
        int minLength = Integer.MAX_VALUE;

        while (left < n && right < n) {
            if (sum >= s) {
                minLength = Math.min(minLength, right - left + 1);

                if (left < right) {
                    sum -= numbers.get(left);
                    left++;
                } else {
                    right++;
                    if (right >= n) {
                        break;
                    }

                    sum += numbers.get(right);
                }

            } else {
                right++;
                if (right >= n) {
                    break;
                }

                sum += numbers.get(right);
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }
    }
}
