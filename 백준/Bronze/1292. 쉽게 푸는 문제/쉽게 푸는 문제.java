import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<String> sequence = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            String s = i + " ";
            List<String> repeat = Arrays.stream(s.repeat(i).split(" "))
                    .collect(Collectors.toList());

            sequence.addAll(repeat);
            if(sequence.size() > 1000) {
                break;
            }
        }

        int result = sequence.stream()
                .skip(list.get(0) - 1)
                .limit(list.get(1) - list.get(0) + 1)
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(result);
    }
}