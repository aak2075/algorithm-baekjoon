import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int x = Integer.parseInt(br.readLine());

        Collections.sort(numbers);

        int left = 0;
        int right = n - 1;

        int count = 0;
        while(left < right) {
            int result = numbers.get(left) + numbers.get(right);
            if (result == x) {
                count++;
            }
            if (result >= x) {
                right--;
            }
            else if (result < x) {
                left++;
            }
        }
        System.out.println(count);
    }
}
