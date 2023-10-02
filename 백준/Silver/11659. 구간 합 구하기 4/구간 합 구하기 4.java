import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<Integer> input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(input.get(0));
        for (int i = 1; i < n; i++) {
            prefixSum.add(prefixSum.get(i - 1) + input.get(i));
        }

        for (int i = 0; i < m; i++) {
            String[] split1 = br.readLine().split(" ");
            int start = Integer.parseInt(split1[0]);
            int end = Integer.parseInt(split1[1]);

            if (start == 1) {
                System.out.println(prefixSum.get(end - 1));
            } else {
                System.out.println(prefixSum.get(end - 1) - prefixSum.get(start - 2));
            }
        }
    }
}

