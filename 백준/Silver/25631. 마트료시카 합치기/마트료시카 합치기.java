import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    private static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);

        List<Integer> input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        int count = input.size();

        int j = 1;
        for (int i = 0; i < input.size() - 1 && j < input.size(); i++) {
            while (j < input.size() - 1 && input.get(i) >= input.get(j)) {
                j++;
            }
            if (input.get(i) < input.get(j)) {
                count--;
                j++;
            }
        }
        System.out.println(count);
    }
}
