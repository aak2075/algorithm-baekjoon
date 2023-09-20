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
            List<Integer> line = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            total.add(line);
        }

        total.sort(Comparator.comparingInt((List<Integer> list) -> list.get(0)).reversed());


        int maxProfit = 0;
        int lastSelect = 987654321;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int select = total.get(i).get(0);

            for (int j = 0; j < total.size(); j++) {
                if (total.get(j).get(0) < select) {
                    continue;
                }
                int profit = select - total.get(j).get(1);

                if (profit > 0) {
                    sum += profit;
                }
            }
            if (sum >= maxProfit) {
                maxProfit = sum;
                lastSelect = select;
            }
        }

        if (maxProfit == 0) {
            lastSelect = 0;
        }

        System.out.println(lastSelect);
    }
}
