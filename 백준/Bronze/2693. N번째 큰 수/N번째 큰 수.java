import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            System.out.println(list.get(2));
        }
    }
}