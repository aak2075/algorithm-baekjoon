import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        int sum = Arrays.stream(scanner.nextLine().split(""))
                .mapToInt(Integer::parseInt)
                .sum();

        System.out.println(sum);
    }
}
