import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String strip = input.strip();
        List<String> result = Arrays.stream(strip.split(" "))
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        System.out.println(result.size());
    }
}
