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

        List<Integer> basket = new ArrayList<>();
        for (int i = n; i > -1; i--) {
            basket.add(i);
        }
        Collections.reverse(basket);

        for (int i = 0; i < m; i++) {
            String[] line = scanner.nextLine().split(" ");
            int leftIndex = Integer.parseInt(line[0]);
            int rightIndex = Integer.parseInt(line[1]);
            int left = basket.get(leftIndex);
            int right = basket.get(rightIndex);

            basket.set(leftIndex, right);
            basket.set(rightIndex, left);
        }

        basket.remove(0);

        String result = basket.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
