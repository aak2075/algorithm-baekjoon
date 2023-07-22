import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static List<Integer> numbers;
    private static List<Integer> operCount;
    private static int n;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());

        numbers = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        operCount = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        dfs(numbers.get(0), 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int num, int idx) {
        if (idx == n) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operCount.get(i) > 0) {
                operCount.set(i, operCount.get(i) - 1);

                switch (i) {
                    case 0: dfs(num + numbers.get(idx), idx + 1); break;
                    case 1: dfs(num - numbers.get(idx), idx + 1); break;
                    case 2: dfs(num * numbers.get(idx), idx + 1); break;
                    case 3: dfs(num / numbers.get(idx), idx + 1); break;
                }

                operCount.set(i, operCount.get(i) + 1);
            }
        }
    }

}