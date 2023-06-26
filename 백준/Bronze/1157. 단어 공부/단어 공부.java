import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        String[] inputs = input.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String s : inputs) {
            if (!map.containsKey(s)) {
                map.put(s, 0);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }

        Integer max = map.values().stream()
                .reduce(0, Math::max);

        Map<String, Integer> result = map.entrySet().stream()
                .filter(e -> e.getValue().equals(max))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        if (result.size() > 1) {
            System.out.println("?");
        } else {
            result.keySet().forEach(k -> System.out.println(k.toUpperCase()));
        }
    }
}