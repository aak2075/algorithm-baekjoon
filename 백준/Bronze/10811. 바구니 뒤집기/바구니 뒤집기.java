import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        List<Integer> basket = new ArrayList<>(Collections.nCopies(n + 1, 0));
        for (int i = 1; i < n + 1; i++) {
            basket.set(i, i);
        }

        for (int i = 0; i < m; i++) {
            String[] line = scanner.nextLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);

            for (int j = start, k = end; j < k; j++, k--) {
                int tmp = basket.get(j);
                basket.set(j, basket.get(k));
                basket.set(k, tmp);
            }
        }
        basket.remove(0);
        String result = basket.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
