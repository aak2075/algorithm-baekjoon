import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Boolean> list = new ArrayList<>(Collections.nCopies(31, false));

        for (int i = 1; i < 29; i++) {
            int input = scanner.nextInt();
            list.set(input, true);
        }

        List<Integer> result = IntStream.range(1, 31)
                .filter(i -> !list.get(i))
                .boxed()
                .collect(Collectors.toList());

        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}
