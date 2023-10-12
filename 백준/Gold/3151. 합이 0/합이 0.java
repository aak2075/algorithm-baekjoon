import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> numbers = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        long answer = 0;

        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                long cnt = numbers.get(i) + numbers.get(j);
                int idx1 = lowerBound(numbers, j + 1, numbers.size(), -cnt);
                int idx2 = upperBound(numbers, j + 1, numbers.size(), -cnt);

                answer += idx2 - idx1;
            }
        }

        System.out.println(answer);
    }

    private static int lowerBound(List<Integer> numbers, int begin, int end, long target) {
        while (begin < end) {
            int mid = (begin + end) / 2;

            if (numbers.get(mid) >= target) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return end;
    }

    private static int upperBound(List<Integer> numbers, int begin, int end, long target) {
        while (begin < end) {
            int mid = (begin + end) / 2;

            if (numbers.get(mid) <= target) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}
