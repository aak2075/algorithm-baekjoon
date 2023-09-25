import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);

        List<Integer> missions = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        missions.addAll(list);

        List<Integer> total = new ArrayList<>();
        BT(0, -1, 0, missions, total, n);

        long count = total.stream()
                .filter(i -> i >= m)
                .count();
        System.out.println(count);
    }

    private static void BT(int index, int before, int letter, List<Integer> missions, List<Integer> total, int size) {
        if (index == size) {
            total.add(letter);
            return;
        }

        for (int i = 0; i < 6; i++) {
            int nextLetter;
            if (before % 3 == i % 3 && before != -1) {
                 nextLetter = letter + missions.get(i) / 2;
            } else {
                nextLetter = letter + missions.get(i);
            }
            BT(index + 1, i, nextLetter, missions, total, size);
        }
    }
}
