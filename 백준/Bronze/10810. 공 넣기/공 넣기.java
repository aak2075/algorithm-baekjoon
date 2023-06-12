import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        List<Integer> basket = new ArrayList<>(Collections.nCopies(n, 0));
        for (int i = 0; i < m; i++) {
            String[] line = scanner.nextLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int number = Integer.parseInt(line[2]);

            for (int j = start - 1; j < end; j++) {
                basket.set(j, number);
            }
        }

        String result = basket.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
