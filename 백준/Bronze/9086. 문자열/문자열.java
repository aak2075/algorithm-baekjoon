import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = Integer.parseInt(scanner.nextLine());

        List<String> result = new ArrayList<>(Collections.nCopies(3, ""));
        for (int i = 0; i < caseNum; i++) {
            String input = scanner.nextLine();
            String unit = input.charAt(0) + String.valueOf(input.charAt(input.length() - 1));
            result.set(i, unit);
        }

        result.forEach(System.out::println);
    }
}
