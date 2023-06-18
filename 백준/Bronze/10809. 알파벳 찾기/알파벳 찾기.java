import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<Character, Integer> charMap = IntStream.rangeClosed('a', 'z')
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(Function.identity(), c -> -1));

        Map<Character, Integer> inputMap = input.chars()
                .mapToObj(c -> (char) c)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), input::indexOf));

        inputMap.keySet().forEach(k -> charMap.put(k, inputMap.get(k)));

        charMap.values().forEach(v -> System.out.print(v + " "));
    }
}
