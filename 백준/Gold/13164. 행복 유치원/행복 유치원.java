import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        List<Integer> input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> diff = new ArrayList<>();

        for (int i = 1; i < input.size(); i++) {
            diff.add(input.get(i) - input.get(i - 1));
        }

        diff.sort(Comparator.comparingInt(Integer::intValue).reversed());

        int sum = diff.stream()
                .skip(k - 1)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }
}

