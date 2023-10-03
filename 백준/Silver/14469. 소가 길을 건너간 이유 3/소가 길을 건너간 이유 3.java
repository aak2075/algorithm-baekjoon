import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> total = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            total.add(input);
        }
        total.sort(Comparator.comparingInt(line -> line.get(0)));

        int time = 0;
        for (List<Integer> cow : total) {
            if (cow.get(0) > time) {
                time = cow.get(0);
            }
            time += cow.get(1);
        }

        System.out.println(time);
    }
}
