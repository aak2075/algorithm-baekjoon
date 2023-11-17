import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int targetCount = Integer.parseInt(br.readLine());
        List<Integer> targets = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        int numberCount = Integer.parseInt(br.readLine());
        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();
        for (Integer number : numbers) {
            int result = upperBound(number, targets) - lowerBound(number, targets);
            answer.add(result);
        }

        String out = answer.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(out);
    }

    private static int upperBound(int target, List<Integer> numbers) {
        int left = 0;
        int right = numbers.size();
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            if (numbers.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
//  l   m  l   r            r
// -10 -10 2 3 3 6 7 10 10 10
//  0   1  2 3 4 5 6 7   8  9
    private static int lowerBound(int target, List<Integer> numbers) {
        int left = 0;
        int right = numbers.size();
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            if (numbers.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
