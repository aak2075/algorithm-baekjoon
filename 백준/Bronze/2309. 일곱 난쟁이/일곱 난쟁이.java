import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int input = Integer.parseInt(scanner.nextLine());
            list.add(input);
        }

        List<Integer> sorted = list.stream()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> result = calcResult(sorted);

        result.forEach(System.out::println);
    }

    private static List<Integer> calcResult(List<Integer> result) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                List<Integer> tmp = new ArrayList<>(result);
                int x = i;
                int y = j;
                List<Integer> step = tmp.stream()
                        .filter(n -> !n.equals(tmp.get(x)) && !n.equals(tmp.get(y)))
                        .collect(Collectors.toList());
                if (step.stream()
                        .mapToInt(s -> s)
                        .sum() == 100) {
                    return new ArrayList<>(step);
                }
            }
        }

        return null;
    }
}