import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);

        List<Integer> broken = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            int input = Integer.parseInt(br.readLine());
            broken.add(input - 1);
        }

        broken.sort(Comparator.comparingInt(Integer::intValue));

        List<Integer> prefixSum = new ArrayList<>();

        int bIndex = 0;
        if (broken.get(0) == 0) {
            prefixSum.add(1);
            bIndex = 1;
        } else {
            prefixSum.add(0);
        }
        for (int i = 1; i < n; i++) {
            if (bIndex == broken.size()) {
                prefixSum.add(prefixSum.get(i - 1));
                continue;
            }
            if (i == broken.get(bIndex)) {
                prefixSum.add(prefixSum.get(i - 1) + 1);
                bIndex++;
            } else {
                prefixSum.add(prefixSum.get(i - 1));
            }
        }

        int min = 987654321;
        for (int i = 0; i < n - k; i++) {
            int window = prefixSum.get(i + k) - prefixSum.get(i);
            min = Math.min(min, window);
        }

        System.out.println(min);
    }
}
