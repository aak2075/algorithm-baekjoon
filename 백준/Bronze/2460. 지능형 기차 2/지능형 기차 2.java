import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String[] input = scanner.nextLine().split(" ");
            int off = Integer.parseInt(input[0]);
            int on = Integer.parseInt(input[1]);

            if (i == 0) {
                list.add(on);
            } else if (i == 9) {
                list.add(off);
            } else {
                list.add(list.get(i - 1) + on - off);
            }
        }
        int max = list.stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow();

        System.out.println(max);
    }
}