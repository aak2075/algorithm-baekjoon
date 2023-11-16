import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        for (Integer number : numbers) {
            int left = 0;
            int right = targets.size() - 1;
            int mid;
            boolean find = false;
            while (left <= right) {
                mid = (left + right) / 2;
                if (targets.get(mid) < number) {
                    left = mid + 1;
                } else if (targets.get(mid) > number) {
                    right = mid - 1;
                } else {
                    System.out.println(1);
                    find = true;
                    break;
                }
            }

            if (!find) {
                System.out.println(0);
            }
        }
    }
}
